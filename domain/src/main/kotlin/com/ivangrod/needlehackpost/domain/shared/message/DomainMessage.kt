package com.ivangrod.needlehackpost.domain.shared.message

import java.io.Serializable
import java.time.Instant
import java.util.*


abstract class DomainMessage : Message {

    private val aggregateId: String
    private var body: Map<String, Serializable>? = null

    protected constructor(aggregateId: String = UUID.randomUUID().toString()) : super() {
        this.aggregateId = aggregateId
    }

    protected constructor(aggregateId: UUID) : this(aggregateId.toString())
    protected constructor(aggregateId: UUID, messageId: String, occurredOn: Instant) :
            super(messageId, occurredOn) {
        this.aggregateId = aggregateId.toString()
    }

    fun aggregateId(): String {
        return aggregateId
    }

    override fun name(): String {
        return super.name()
    }

    override fun serialize(): Map<String, Serializable>? {
        return HashMap<String, Serializable>(
            java.util.Map.of(
                "data", HashMap(
                    java.util.Map.of(
                        "id", messageId(),
                        "type", name(),
                        "occurred_on", occurredOn().toString(),
                        // TODO
                        // "attributes", body()
                    )
                ),
                "meta", java.util.HashMap<Any, Any>()
            )
        )
    }

    override fun hashCode(): Int {
        return Objects.hash(aggregateId())
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as DomainMessage
        return aggregateId() == that.aggregateId()
    }
}
