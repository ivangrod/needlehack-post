package com.ivangrod.needlehackpost.infrastructure.post.messaging

import com.ivangrod.needlehackpost.domain.post.PostDate
import com.ivangrod.needlehackpost.domain.post.PostTitle
import com.ivangrod.needlehackpost.domain.post.PostUri
import com.ivangrod.needlehackpost.domain.post.SenderPost
import com.ivangrod.needlehackpost.infrastructure.post.messaging.dto.PostMessage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate

class RabbitmqSenderPost(private val rabbitTemplate: RabbitTemplate) : SenderPost {

    override fun send(title: PostTitle, uri: PostUri, publishedDate: PostDate) {

        log.info("Send post with title ${title.value} to message broker")

        rabbitTemplate.convertAndSend(
            "needlehack-post-exchange",
            "needlehack-post.routing-key",
            Json.encodeToString(
                PostMessage(title.value ?: "", uri.value ?: "", publishedDate.publicationAt.toString())
            )
        )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RabbitmqSenderPost::class.java)
    }
}
