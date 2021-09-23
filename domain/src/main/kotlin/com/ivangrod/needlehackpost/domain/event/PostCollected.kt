package com.ivangrod.needlehackpost.domain.event

import com.ivangrod.needlehackpost.domain.shared.format.DateFormatter.dateToString
import com.ivangrod.needlehackpost.domain.shared.message.DomainEvent
import java.io.Serializable
import java.time.LocalDateTime


class PostCollected : DomainEvent {
    val title: String?
    val uri: String?
    val source: String?
    val author: String?
    val feedUri: String?
    val content: String?
    val topics: Set<String>
    val publishedAt: LocalDateTime?

    constructor() : super(null) {
        title = null
        uri = null
        source = null
        publishedAt = null
        author = null
        feedUri = null
        content = null
        topics = emptySet()
    }

    constructor(
        aggregateId: String?, title: String?, uri: String?, source: String?,
        publishedAt: LocalDateTime?
    ) : super(aggregateId) {
        this.title = title
        this.uri = uri
        this.source = source
        this.publishedAt = publishedAt
        author = null
        feedUri = null
        content = null
        topics = emptySet()
    }

    constructor(
        aggregateId: String?, eventId: String?, happenedAt: LocalDateTime?, title: String?,
        uri: String?, source: String?, publishedAt: LocalDateTime?
    ) : super(aggregateId, eventId, happenedAt) {
        this.title = title
        this.uri = uri
        this.source = source
        this.publishedAt = publishedAt
        author = null
        feedUri = null
        content = null
        topics = emptySet()
    }

    constructor(
        aggregateId: String?, title: String?,
        uri: String?, source: String?, author: String?, feedUri: String?, content: String?, topics: Set<String?>,
        publishedAt: LocalDateTime?
    ) : super(aggregateId) {
        this.title = title
        this.uri = uri
        this.source = source
        this.author = author
        this.feedUri = feedUri
        this.content = content
        this.topics = topics as Set<String>
        this.publishedAt = publishedAt
    }

    override fun eventName(): String? {
        return "post.collected"
    }

    override fun toPrimitives(): HashMap<String?, Serializable?>? {
        return object : HashMap<String?, Serializable?>() {
            init {
                put("title", title)
                put("uri", uri)
                put("source", source)
                put("author", author)
                put("feedUri", feedUri)
                put("content", content)
                put("topics", topics.joinToString { "," })
                put("publishedAt", dateToString(publishedAt))
            }
        }
    }

    override fun fromPrimitives(
        aggregateId: String?, body: Map<String?, Serializable?>?,
        eventId: String?, happenedAt: LocalDateTime?
    ): DomainEvent {
        return PostCollected(
            aggregateId,
            body?.get("title") as String?,
            body?.get("uri") as String?,
            body?.get("source") as String?,
            body?.get("author") as String?,
            body?.get("feedUri") as String?,
            body?.get("content") as String?,
            (body?.get("topics") as String?)!!.split(",").toSet(),
            body?.get("publishedAt") as LocalDateTime?
        )
    }
}
