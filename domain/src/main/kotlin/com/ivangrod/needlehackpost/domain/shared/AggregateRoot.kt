package com.ivangrod.needlehackpost.domain.shared

import com.ivangrod.needlehackpost.domain.shared.message.DomainEvent
import java.util.*
import kotlin.collections.ArrayList

abstract class AggregateRoot {

    private var domainEvents: MutableList<DomainEvent> = ArrayList()

    protected fun record(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun recordedEvents(): List<DomainEvent> {
        val events: List<DomainEvent> = domainEvents
        domainEvents.clear()
        return events
    }
}
