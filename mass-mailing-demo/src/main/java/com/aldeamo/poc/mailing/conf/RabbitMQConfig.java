package com.aldeamo.poc.mailing.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aldeamo.poc.mailing.model.EmailQueueReceiver;

@Configuration
public class RabbitMQConfig {
    public final static String EMAIL_QUEUE_NAME = "aldeamail-outgoing-email";

    @Bean
    Queue queue() {
        return new Queue(EMAIL_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("aldeamail-outgoing-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(EMAIL_QUEUE_NAME);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(EMAIL_QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    EmailQueueReceiver emailQueueReceiver() {
        return new EmailQueueReceiver();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(EmailQueueReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
