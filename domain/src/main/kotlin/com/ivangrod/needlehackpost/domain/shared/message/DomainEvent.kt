package com.ivangrod.needlehackpost.domain.shared.message

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*


abstract class DomainEvent {
    private var aggregateId: String? = null
    private var eventId: String? = null
    private var happenedAt: LocalDateTime? = null

    constructor(aggregateId: String?) {
        this.aggregateId = aggregateId
        eventId = UUID.randomUUID().toString()
        happenedAt = LocalDateTime.now()
    }

    constructor(aggregateId: String?, eventId: String?, happenedAt: LocalDateTime?) {
        this.aggregateId = aggregateId
        this.eventId = eventId
        this.happenedAt = happenedAt
    }

    protected constructor()

    abstract fun eventName(): String?
    abstract fun toPrimitives(): Map<String?, Serializable?>?
    abstract fun fromPrimitives(
        aggregateId: String?,
        body: Map<String?, Serializable?>?,
        eventId: String?,
        happenedAt: LocalDateTime?
    ): DomainEvent?

    fun aggregateId(): String? {
        return aggregateId
    }

    fun eventId(): String? {
        return eventId
    }

    fun happenedAt(): LocalDateTime? {
        return happenedAt
    }
}
