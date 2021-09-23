package com.ivangrod.needlehackpost.application.post.command.store_post

import com.ivangrod.needlehackpost.domain.post.Post
import com.ivangrod.needlehackpost.domain.post.Posts
import com.ivangrod.needlehackpost.domain.shared.message.EventBus


class StorePostHandler(private val posts: Posts, private val eventBus: EventBus) {

    fun execute(params: StorePost) {
        val postFromFeed: Post = Post
            .create(
                params.getTitle(),
                params.getUri(),
                params.getAuthor(),
                params.getFeed(),
                params.getContent(),
                params.getPublicationDate(),
                params.getTopics()
            )
        posts.save(postFromFeed)
        eventBus.publish(postFromFeed.recordedEvents())
    }
}
