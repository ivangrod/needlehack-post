package com.ivangrod.needlehackpost.infrastructure.shared.bus.event.spring

import com.ivangrod.needlehackpost.domain.shared.message.DomainEvent
import com.ivangrod.needlehackpost.domain.shared.message.EventBus
import com.ivangrod.needlehackpost.infrastructure.post.event.CollectPostListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import java.util.function.Consumer


class SpringApplicationEventBus(private val publisher: ApplicationEventPublisher) : EventBus {

    override fun publish(events: List<DomainEvent?>?) {
        events?.forEach(Consumer { event: DomainEvent? -> this.publish(event) })
    }

    private fun publish(event: DomainEvent?) {
        event?.let { publisher.publishEvent(it) }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SpringApplicationEventBus::class.java)
    }
}
