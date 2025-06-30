package com.work.logistics.config;

import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource(value = "classpath:RabbitMQ.yml" , factory = YamlPropertySourceFactory.class)
public class RabbitConfig {

    @Value("${requestQueue}")
    private String requestQueue;

    @Value("${requestExchange}")
    private String requestExchange;

    @Value("${requestRoutingKey}")
    private String requestRoutingKey;

    @Value("${virtualHost}")
    private String virtualHost;

    @Value("${rmqHost}")
    private String rmqHost;

    @Value("${rmqPort}")
    private int rmqPort;

    @Value("${rmqUser}")
    private String rmqUser;

    @Value("${rmqPassword}")
    private String rmqPassword;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rmqHost);          // 建议也通过 @Value 注入
        factory.setPort(rmqPort);
        factory.setUsername(rmqUser);
        factory.setPassword(rmqPassword);
        factory.setVirtualHost(virtualHost);   // 关键！设置虚拟主机
        // 关键配置：心跳检测（单位：秒）
        factory.setRequestedHeartBeat(30); // 每 30 秒发送一次心跳
        // 连接超时（单位：毫秒）
        factory.setConnectionTimeout(3000); // 30 秒超时
        return factory;
    }

    @Bean
    public Queue requestQueue() {
        return new Queue(requestQueue, true);
    }


    @Bean
    public DirectExchange  requestExchange() {
        return new DirectExchange(requestExchange, true,  false);
    }

    @Bean
    public Binding  requestBinding() {
        return BindingBuilder
                .bind(requestQueue())
                .to(requestExchange())
                .with(requestRoutingKey);
    }

}

