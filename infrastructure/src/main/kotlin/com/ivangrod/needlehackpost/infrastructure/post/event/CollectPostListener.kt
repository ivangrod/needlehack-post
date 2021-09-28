package com.ivangrod.needlehackpost.infrastructure.post.event

import com.ivangrod.needlehackpost.application.post.command.store_post.StorePost
import com.ivangrod.needlehackpost.application.post.command.store_post.StorePostHandler
import com.ivangrod.needlehackpost.domain.event.PostCollected
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener


class CollectPostListener(private val storePostHandler: StorePostHandler) {

    @EventListener
    fun on(event: PostCollected) {
        log.info("CollectPostListener $event")

        // TODO Not invoke use case from here
       event.title?.let {
           val storePostParams = StorePost(
                it,
                event.uri!!, event.author!!, event.feedUri!!, event.source!!,
                event.content!!, event.publishedAt!!, event.topics
            )
           storePostHandler.execute(storePostParams)
       }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CollectPostListener::class.java)
    }
}
