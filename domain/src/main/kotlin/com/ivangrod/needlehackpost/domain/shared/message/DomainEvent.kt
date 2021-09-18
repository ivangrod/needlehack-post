package com.ivangrod.needlehackpost.domain.shared.message

import com.ivangrod.needlehackpost.domain.shared.AggregateId
import java.time.Instant
import java.util.*


abstract class DomainEvent : DomainMessage {
    protected constructor(aggregateId: AggregateId) : super(aggregateId.uuid())
    protected constructor(aggregateId: UUID, messageId: String, occurredOn: Instant) : super(
        aggregateId,
        messageId,
        occurredOn
    )

    override fun type(): MessageType {
        return MessageType.EVENT
    }
}
