package com.boards.config.RMQConfig;

import org.springframework.amqp.core.Binding;

// import javax.naming.Binding;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RPC_MESSAGE_QUEUE = "todolist_queue";
    public static final String RPC_REPLY_MESSAGE_QUEUE = "todolist_queue_reply";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    /** *
     * Configure the Send Message Queue
     */
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
    TopicExchange todolistTopicExchange() {
        return new TopicExchange(RPC_EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding todolistMsgBinding() {
        return BindingBuilder.bind(todolistMsgQueue())
                .to(todolistTopicExchange())
                .with(RPC_MESSAGE_QUEUE);
    }
    /** *
     * Back to Queue and Switch Link
     */
    @Bean
    Binding todolistReplyBinding() {
        return BindingBuilder.bind(todolistReplyQueue())
                .to(todolistTopicExchange())
                .with(RPC_REPLY_MESSAGE_QUEUE);
    }

}