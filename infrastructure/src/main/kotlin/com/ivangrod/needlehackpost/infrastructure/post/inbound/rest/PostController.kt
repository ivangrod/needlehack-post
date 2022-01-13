package com.ivangrod.needlehackpost.infrastructure.post.inbound.rest

import com.ivangrod.needlehackpost.application.post.command.store_post.StorePost
import com.ivangrod.needlehackpost.application.post.command.store_post.StorePostHandler
import com.ivangrod.needlehackpost.infrastructure.post.inbound.dto.PostDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class PostController(val storePost: StorePostHandler) {

    @PostMapping("/post")
    fun posts(@RequestBody post: PostDTO) = storePost.execute(
        StorePost(
            post.title,
            post.uri,
            post.author,
            post.feedUri,
            post.feedSource,
            post.content,
            LocalDateTime.parse(post.publicationDate),
            post.topics
        )
    )
}
