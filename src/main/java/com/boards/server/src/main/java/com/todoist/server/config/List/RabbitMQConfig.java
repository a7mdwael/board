package com.todoist.server.config.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RPC_MESSAGE_QUEUE = "todolist_queue";
    public static final String RPC_REPLY_MESSAGE_QUEUE = "todolist_queue_reply";
    public static final String RPC_EXCHANGE = "rpc_exchange";


    /** *
     * Set sending RPCQueue message
     Configure the Send Message Queue*/
    @Bean
    Queue todolistMsgQueue() {
        return new Queue(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Return Queue Configuration
     */
    @Bean
    Queue todolistReplyQueue() {

        return new Queue(RPC_REPLY_MESSAGE_QUEUE);
    }
    /** *
     * Switch setting
     */
    @Bean
    TopicExchange todolistExchange() {

        return new TopicExchange(RPC_EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding todolistMsgBinding() {

        return BindingBuilder.bind(todolistMsgQueue()).to(todolistExchange()).with(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Back to Queue and Switch Link
     */
    @Bean
    Binding todolistReplyBinding() {

        return BindingBuilder.bind(todolistReplyQueue()).to(todolistExchange()).with(RPC_REPLY_MESSAGE_QUEUE);
    }
    /** *
     * Use RabbitTemplate Send and receive messages
     * And set callback queue address
     */
    @Bean
    RabbitTemplate todolistRabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(RPC_REPLY_MESSAGE_QUEUE);
        template.setReplyTimeout(10000);
        return template;
    }
    /** *
     * Configure listener for return queue
     */
    @Bean
    SimpleMessageListenerContainer todolistReplyContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RPC_REPLY_MESSAGE_QUEUE);
        container.setMessageListener(todolistRabbitTemplate(connectionFactory));
        return container;
    }

}
