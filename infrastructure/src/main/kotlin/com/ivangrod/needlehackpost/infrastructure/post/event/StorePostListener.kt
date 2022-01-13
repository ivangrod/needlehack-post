package com.ivangrod.needlehackpost.infrastructure.post.event

import com.ivangrod.needlehackpost.application.post.command.store_post.StorePost
import com.ivangrod.needlehackpost.application.post.command.store_post.StorePostHandler
import com.ivangrod.needlehackpost.domain.event.PostStored
import com.ivangrod.needlehackpost.domain.post.PostDate
import com.ivangrod.needlehackpost.domain.post.PostTitle
import com.ivangrod.needlehackpost.domain.post.PostUri
import com.ivangrod.needlehackpost.domain.post.SenderPost
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener


class StorePostListener(private val senderPost: SenderPost) {

    @EventListener
    fun on(event: PostStored) {
        log.info("StorePostListener $event")

        // TODO Use case?
       event.title?.let {
           senderPost.send(PostTitle(it), PostUri(event.uri), PostDate(event.publishedAt!!))
       }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(StorePostListener::class.java)
    }
}
