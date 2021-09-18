package com.ivangrod.needlehackpost.domain.shared.message

import java.time.Instant
import java.util.*

abstract class Command : DomainMessage {

    protected constructor() : super()
    protected constructor(aggregateId: String?) : super(aggregateId!!)
    protected constructor(aggregateId: UUID?) : super(aggregateId!!)
    protected constructor(aggregateId: UUID, messageId: String, occurredOn: Instant) : super(
        aggregateId,
        messageId!!,
        occurredOn!!
    )

    override fun type(): MessageType? {
        return MessageType.COMMAND
    }
}
