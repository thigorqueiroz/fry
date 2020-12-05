package com.thigorqueiroz.fry.port.adapter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class AmqpConfig {

    //TODO: put it in a right place
    public static final String PARTNER_CREATED_QUEUE = "partner_api.partner_created";
    private static final String PARTNER_CREATED_ROUTING_KEY = "resource.partner.created";

    @Bean
    public TopicExchange defaultExchange(@Value("${spring.rabbitmq.template.exchange}") String defaultTopic) {
        return new TopicExchange(defaultTopic);
    }

    @Bean
    public InitializingBean startConfig(AmqpAdmin amqpAdmin, TopicExchange defaultTopic) {
        //TODO: externalize queues in a static class/enum
        return () -> setupQueue(amqpAdmin, defaultTopic, PARTNER_CREATED_QUEUE, PARTNER_CREATED_ROUTING_KEY);
    }

    private void setupQueue(AmqpAdmin amqpAdmin, TopicExchange exchange, String queueName, String routingKey) {
        //TODO: create dlq config for each queue

        var queue = new Queue(queueName, Boolean.TRUE);
        var binding  = BindingBuilder.bind(queue).to(exchange).with(routingKey);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}