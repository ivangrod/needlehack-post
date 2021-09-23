package com.ivangrod.needlehackpost.domain.event

import com.ivangrod.needlehackpost.domain.shared.format.DateFormatter.dateToString
import com.ivangrod.needlehackpost.domain.shared.message.DomainEvent
import java.io.Serializable
import java.time.LocalDateTime


class PostStored : DomainEvent {
    private val title: String?
    private val uri: String?
    private val publishedAt: LocalDateTime?

    constructor() : super(null) {
        title = null
        uri = null
        publishedAt = null
    }

    constructor(aggregateId: String?, title: String?, uri: String?, publishedAt: LocalDateTime?) : super(aggregateId) {
        this.title = title
        this.uri = uri
        this.publishedAt = publishedAt
    }

    constructor(
        aggregateId: String?, eventId: String?, happenedAt: LocalDateTime?, title: String?,
        uri: String?, publicatedAt: LocalDateTime?
    ) : super(aggregateId, eventId!!, happenedAt) {
        this.title = title
        this.uri = uri
        this.publishedAt = publicatedAt
    }

    override fun eventName(): String {
        return "post.created"
    }

    override fun toPrimitives(): HashMap<String?, Serializable?> {
        return object : HashMap<String?, Serializable?>() {
            init {
                put("title", title)
                put("uri", uri)
                put("publishedAt", dateToString(publishedAt))
            }
        }
    }

    override fun fromPrimitives(
        aggregateId: String?, body: Map<String?, Serializable?>?,
        eventId: String?, happenedAt: LocalDateTime?
    ): DomainEvent {
        return PostStored(
            aggregateId,
            eventId,
            happenedAt,
            body?.get("title") as String,
            body["uri"] as String,
            body["publishedAt"] as LocalDateTime
        )
    }
}
