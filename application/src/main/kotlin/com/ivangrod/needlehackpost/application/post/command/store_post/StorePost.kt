package com.ivangrod.needlehackpost.application.post.command.store_post

import com.ivangrod.needlehackpost.domain.post.*
import java.time.LocalDateTime


class StorePost(
    private val title: String, private val uri: String, private val author: String, private val feedUri: String,
    private val feedSource: String, private val content: String, private val publicationDate: LocalDateTime,
    private val topics: Set<String>
) {
    fun getTitle(): PostTitle {
        return PostTitle(title)
    }

    fun getUri(): PostUri {
        return PostUri(uri)
    }

    fun getAuthor(): Author {
        return Author(author)
    }

    fun getFeed(): Feed {
        return Feed(feedUri, feedSource)
    }

    fun getContent(): PostContent {
        return PostContent.buildWithContentPlain(content)
    }

    fun getPublicationDate(): PostDate {
        return PostDate(publicationDate)
    }

    fun getTopics(): Set<Topic> {
        return topics.map { Topic(it) }.toSet()
    }
}
