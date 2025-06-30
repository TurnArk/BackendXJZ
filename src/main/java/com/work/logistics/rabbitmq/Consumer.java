package com.work.logistics.rabbitmq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.work.logistics.entity.Orders;
import com.work.logistics.mapper.OrdersMapper;
import com.work.logistics.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class.getName());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private Properties mailProperties;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.password}")
    private String password;

    @RabbitListener(queues = "${requestQueue}")
    public void receive(String jsonStr, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){

        try{
            JsonNode json = objectMapper.readTree(jsonStr);
            String orderId = json.get("orderId").asText();
            String newStatus = json.get("newStatus").asText();
            String location = json.get("location").asText();
            String deliverymanId = json.get("deliverymanId").asText();

            Orders order = ordersMapper.selectById(orderId);

            Session session = Session.getInstance(mailProperties,new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            String userId = order.getUserId();
            String email = userService.getUserById(userId).getEmail();

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
                message.setSubject("物流状态变更");
                String text="订单号: "+orderId
                        +"\n物流状态: "+newStatus
                        +"\n您的货物已经运送到: "+ location
                        +"\n送货员: "+deliverymanId;
                message.setText(text);
                Transport.send(message);
                logger.info("邮件发送成功");

                channel.basicAck(deliveryTag, false);
            }catch (MessagingException e){
                e.printStackTrace();
                logger.error("邮件发送失败: " + e.getMessage());
                try{
                    channel.basicNack(deliveryTag, false, true);
                }catch (IOException ioe){
                    logger.error("重新放入队列数据错误: {}", ioe.getMessage());
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
