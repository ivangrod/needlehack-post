package com.ivangrod.needlehackpost.infrastructure.post.di

import com.ivangrod.needlehackpost.application.post.command.collect_post.CollectPostHandler
import com.ivangrod.needlehackpost.application.post.command.store_post.StorePostHandler
import com.ivangrod.needlehackpost.domain.post.FeedExtractor
import com.ivangrod.needlehackpost.domain.post.Posts
import com.ivangrod.needlehackpost.domain.post.SenderPost
import com.ivangrod.needlehackpost.domain.shared.message.EventBus
import com.ivangrod.needlehackpost.infrastructure.post.event.CollectPostListener
import com.ivangrod.needlehackpost.infrastructure.post.event.StorePostListener
import com.ivangrod.needlehackpost.infrastructure.post.messaging.RabbitmqSenderPost
import com.ivangrod.needlehackpost.infrastructure.post.persistence.model.JpaPosts
import com.ivangrod.needlehackpost.infrastructure.post.persistence.repository.JpaPostRepository
import com.ivangrod.needlehackpost.infrastructure.post.service.RssFeedExtractor
import com.ivangrod.needlehackpost.infrastructure.post.service.RssReader
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class PostBeanInitializr {

    @Bean
    fun posts(jpaPostRepository: JpaPostRepository): Posts {
        return JpaPosts(jpaPostRepository)
    }

    @Bean
    fun feedExtractor(): FeedExtractor {
        return RssFeedExtractor()
    }

    @Bean
    fun storePost(
        jpaPosts: Posts,
        springApplicationEventBus: EventBus
    ): StorePostHandler {
        return StorePostHandler(jpaPosts, springApplicationEventBus)
    }

    @Bean
    fun collectPost(
        feedExtractor: FeedExtractor,
        springApplicationEventBus: EventBus
    ): CollectPostHandler {
        return CollectPostHandler(feedExtractor, springApplicationEventBus)
    }

    @Bean
    fun collectPostListener(storePost: StorePostHandler): CollectPostListener {
        return CollectPostListener(storePost)
    }

    @Bean
    fun rssReader(
        @Value("classpath:rss/engineering_blogs.opml") resourceOpml: Resource,
        collectorPostHandler: CollectPostHandler
    ): RssReader {
        return RssReader(resourceOpml, collectorPostHandler)
    }

    @Bean
    fun senderPost(rabbitTemplate: RabbitTemplate): SenderPost {
        return RabbitmqSenderPost(rabbitTemplate)
    }

    @Bean
    fun storePostListener(senderPost: SenderPost): StorePostListener {
        return StorePostListener(senderPost)
    }
}
