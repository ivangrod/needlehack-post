package com.ivangrod.needlehackpost.domain.shared.message

interface EventBus {
    fun publish(events: List<DomainEvent?>?)
}
