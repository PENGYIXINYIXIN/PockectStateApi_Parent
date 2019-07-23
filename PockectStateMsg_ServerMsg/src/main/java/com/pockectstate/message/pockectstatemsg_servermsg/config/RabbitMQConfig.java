package com.pockectstate.message.pockectstatemsg_servermsg.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


/**
 * @author:Yixi
 * @date:2019/7/22
 */
public class RabbitMQConfig {

    public static String queuelog="EsTaskLog";
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Bean
    public Queue createQueue(){
        return new Queue(queuelog,false,false,false);
    }
    @Bean
    public RabbitTemplate createRT(){
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
}
