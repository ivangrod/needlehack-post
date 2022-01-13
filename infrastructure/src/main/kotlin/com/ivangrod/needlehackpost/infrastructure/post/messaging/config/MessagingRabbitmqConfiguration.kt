package com.ivangrod.needlehackpost.infrastructure.post.messaging.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MessagingRabbitmqConfiguration {

    val topicExchangeName = "needlehack-post-exchange"

    val queueName = "needlehack-post"

    @Bean
    fun queue(): Queue? {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange? {
        return TopicExchange(topicExchangeName)
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with("needlehack-post.#")
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory?
    ): SimpleMessageListenerContainer? {
        val container = SimpleMessageListenerContainer()
        connectionFactory?.let {
            container.connectionFactory = it
        }
        container.setQueueNames(queueName)
        return container
    }
}

