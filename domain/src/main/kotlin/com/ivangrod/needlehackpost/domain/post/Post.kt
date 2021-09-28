package com.ivangrod.needlehackpost.domain.post

import com.ivangrod.needlehackpost.domain.event.PostCollected
import com.ivangrod.needlehackpost.domain.event.PostStored
import com.ivangrod.needlehackpost.domain.shared.AggregateRoot
import java.io.Serializable
import kotlin.collections.HashMap


class Post private constructor(
    title: PostTitle, uri: PostUri, creator: Author, origin: Feed,
    content: PostContent, dates: PostDate, topics: Set<Topic>
) : AggregateRoot() {

    val id: PostId
    val title: PostTitle
    val uri: PostUri
    val creator: Author
    val origin: Feed
    val content: PostContent
    val dates: PostDate
    val topics: Set<Topic>

    fun toPrimitives(): HashMap<String?, Serializable?> {
        return object : HashMap<String?, Serializable?>() {
            init {
                put("title", title.value)
                put("uri", uri.value)
                put("creator", creator.value)
                put("origin", origin.source)
                put("feedUri", origin.uri)
                put("content", content.value)
                put("publication_date", dates.publicationDateToStringFormat())
                put("topics", topics.map { it.value }.joinToString { "," }
                )
            }
        }
    }

    companion object {

        fun create(
            title: PostTitle, uri: PostUri, creator: Author,
            origin: Feed, content: PostContent, dates: PostDate, topics: Set<Topic>
        ): Post {
            val post = Post(title, uri, creator, origin, content, dates, topics)
            post.record(
                PostStored(post.id.value, title.value, uri.value, dates.publicationAt)
            )
            return post
        }

        fun collect(
            id: PostId, title: PostTitle, uri: PostUri, creator: Author,
            origin: Feed, content: PostContent, dates: PostDate, topics: Set<Topic>
        ): Post {
            val post = Post(title, uri, creator, origin, content, dates, topics)
            post.record(
                PostCollected(
                    id.value, title.value, uri.value, origin.source,
                    creator.value, origin.uri, content.value,
                    topics.map { it.value }.toSet(),
                    dates.publicationAt
                )
            )
            return post
        }

        fun fromPrimitives(plainData: Map<String?, Any?>): Post {
            return Post(
                PostTitle(plainData["title"] as String?),
                PostUri(plainData["uri"] as String?),
                Author(plainData["creator"] as String?),
                Feed((plainData["origin"] as String?)!!, (plainData["feedUri"] as String?)!!),
                PostContent.buildWithContentPlain(plainData["content"] as String?),
                PostDate.buildStringToPublicationDate(plainData["publication_date"] as String?),
                (plainData["topics"] as String?)!!.split(",").map { Topic(it) }.toSet()
            )
        }
    }

    init {
        this.id = PostId.buildFromUri(uri.value!!)
        this.title = title
        this.uri = uri
        this.creator = creator
        this.origin = origin
        this.content = content
        this.dates = dates
        this.topics = topics
    }
}

