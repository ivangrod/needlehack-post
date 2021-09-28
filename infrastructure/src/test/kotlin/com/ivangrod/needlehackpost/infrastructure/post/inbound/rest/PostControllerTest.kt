package com.ivangrod.needlehackpost.infrastructure.post.inbound.rest

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostControllerTest {

    @Autowired
    lateinit var controller: PostController

    @Test
    fun should_return_all_posts(){
        //assertThat(controller.posts()).isNotEmpty()
    }
}
