package com.ivangrod.needlehackpost.infrastructure.post.messaging.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MessagingRabbitmqConfiguration {

    val directExchangeName = "needlehack-post-exchange"

    val queueName = "needlehack-post"

    @Bean
    fun queue(): Queue? {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): DirectExchange? {
        return DirectExchange(directExchangeName)
    }

    @Bean
    fun binding(queue: Queue?, exchange: DirectExchange?): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with("needlehack-post.routing-key")
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

