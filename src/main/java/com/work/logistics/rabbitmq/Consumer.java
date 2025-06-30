package com.work.logistics.rabbitmq;

import com.work.logistics.entity.Orders;
import com.work.logistics.mapper.OrdersMapper;
import com.work.logistics.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class.getName());

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

    @RabbitListener(queues = "${resultQueue}")
    public void receive(String orderId, String newStatus, String location, String deliverymanId){
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
        }catch (MessagingException e){
            e.printStackTrace();
            logger.error("邮件发送失败: " + e.getMessage());
        }
    }
}
