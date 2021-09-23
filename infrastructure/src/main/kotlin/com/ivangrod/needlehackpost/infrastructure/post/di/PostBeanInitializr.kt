package com.ivangrod.needlehackpost.infrastructure.post.di

import com.ivangrod.needlehackpost.application.post.command.store_post.StorePostHandler
import com.ivangrod.needlehackpost.domain.post.Posts
import com.ivangrod.needlehackpost.domain.shared.message.EventBus
import com.ivangrod.needlehackpost.infrastructure.post.persistence.model.JpaPost
import com.ivangrod.needlehackpost.infrastructure.post.persistence.model.JpaPosts
import com.ivangrod.needlehackpost.infrastructure.post.persistence.repository.JpaPostRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@Configuration
class PostBeanInitializr {

    @Bean
    fun posts(jpaPostRepository: JpaPostRepository): Posts {
        return JpaPosts(jpaPostRepository)
    }

    @Bean
    fun storePost(
        jpaPosts: Posts,
        springApplicationEventBus: EventBus
    ): StorePostHandler {
        return StorePostHandler(jpaPosts, springApplicationEventBus)
    }
}
