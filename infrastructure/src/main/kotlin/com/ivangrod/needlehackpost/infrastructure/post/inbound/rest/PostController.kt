package com.ivangrod.needlehackpost.infrastructure.post.inbound.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {

    @GetMapping("/post")
    fun posts() = "need to implement"
}
