package com.ivangrod.needlehackpost.domain.shared

import com.ivangrod.needlehackpost.domain.shared.message.DomainEvent
import java.util.*
import kotlin.collections.ArrayList

abstract class AggregateRoot {

    private var domainEvents: MutableList<DomainEvent> = ArrayList()

    abstract fun id(): AggregateId?

    protected fun record(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun recordedEvents(): List<DomainEvent> {
        val events: List<DomainEvent> = domainEvents
        domainEvents.clear()
        return events
    }

    override fun hashCode(): Int {
        return Objects.hash(id())
    }

    override fun equals(aggregateRoot: Any?): Boolean {
        if (this === aggregateRoot) {
            return true
        }
        return if (aggregateRoot !is AggregateRoot) false else Objects.equals(id(), aggregateRoot.id())
    }
}
