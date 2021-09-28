package com.ivangrod.needlehackpost.application.post.command.collect_post

import com.ivangrod.needlehackpost.domain.post.Feed
import com.ivangrod.needlehackpost.domain.post.FeedExtractor
import com.ivangrod.needlehackpost.domain.post.Post
import com.ivangrod.needlehackpost.domain.shared.message.EventBus
import java.util.function.Consumer

class CollectPostHandler(private val feedExtractor: FeedExtractor, private val eventBus: EventBus) {

    fun execute(params: CollectPost): List<Post?>? {
        val feed: Feed = params.createFeed()
        val postsCollected = feedExtractor.extract(feed)
        postsCollected!!.forEach(Consumer { post: Post? -> eventBus.publish(post?.recordedEvents()) })
        return postsCollected
    }

}
