package com.work.logistics.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.logistics.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Component
public class Publish {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitConfig rabbitConfig;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String orderId, String newStatus, String location, String deliverymanId) throws Exception{
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);

        Map<String, String> message = new HashMap<>();
        message.put("orderId", orderId);
        message.put("newStatus", newStatus);
        message.put("location", location);
        message.put("deliverymanId", deliverymanId);

        String json = objectMapper.writeValueAsString(message);//将json转变为字符串
        Message amqpMessage = new Message(json.getBytes(StandardCharsets.UTF_8),messageProperties);

        rabbitTemplate.send(
                rabbitConfig.getRequestExchange(),
                rabbitConfig.getRequestRoutingKey(),
                amqpMessage
        );
        System.out.println("发送消息：" + message);
    }
}
