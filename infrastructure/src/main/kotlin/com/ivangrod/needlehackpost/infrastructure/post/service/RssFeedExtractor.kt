package com.ivangrod.needlehackpost.infrastructure.post.service

import com.ivangrod.needlehackpost.domain.post.*
import com.ivangrod.needlehackpost.infrastructure.shared.format.JsoupProcessor
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.CollectionUtils
import org.apache.commons.lang3.StringUtils
import java.net.URL
import java.util.stream.Collectors


internal class RssFeedExtractor : FeedExtractor {

    override fun extract(feed: Feed?): List<Post?> {
        log.info("Extracting RSS from feed ${feed!!.source}")
        val postsCollected: MutableList<Post?> = ArrayList()
        try {
            val input = SyndFeedInput()
            val feedLoaded: SyndFeed = input.build(XmlReader(URL(feed.uri)))
            postsCollected.addAll(feedLoaded.entries
                .stream()
                .map { entry ->
                    Post
                        .collect(
                            PostId.buildFromUri(entry.link),
                            PostTitle(entry.title),
                            PostUri(entry.link),
                            Author(entry.author),
                            feed,
                            extractContent(entry),
                            if (entry.publishedDate != null) PostDate(entry.publishedDate) else PostDate(
                                entry.updatedDate
                            ),
                            extractTopics(entry)
                        )
                }
                .collect(Collectors.toList()))
        } catch (exception: Exception) {
            log.error(
                "An error has been produced when the feed from source [{}] was loaded",
                feed.source
            )
        }
        return postsCollected
    }

    private fun extractContent(entryFromFeed: SyndEntry): PostContent {
        var content = PostContent.buildWithContentPlain(StringUtils.EMPTY)
        if (!CollectionUtils.isEmpty(entryFromFeed.contents)) {
            val strBuilder = StringBuilder()
            entryFromFeed.contents
                .forEach { contentFromEntry -> strBuilder.append(contentFromEntry.value) }
            content = PostContent.buildWithContentProcessed(strBuilder.toString(), JsoupProcessor())
        } else if (entryFromFeed.description != null) {
            content = PostContent.buildWithContentProcessed(
                entryFromFeed.description.value,
                JsoupProcessor()
            )
        }
        return content
    }

    private fun extractTopics(entryFromFeed: SyndEntry): Set<Topic> {
        var topics: Set<Topic> = HashSet()
        if (!CollectionUtils.isEmpty(entryFromFeed.categories)) {
            topics = entryFromFeed.categories
                .stream()
                .map { entryCategory -> Topic(entryCategory.name) }
                .collect(Collectors.toSet())
        }
        return topics
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RssFeedExtractor::class.java)
    }
}
