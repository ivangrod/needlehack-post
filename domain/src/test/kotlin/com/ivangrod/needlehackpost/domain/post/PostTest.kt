package com.ivangrod.needlehackpost.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PostTest {

    @Test
    fun should_return_link() {
        assertThat(Post().link()).isNotEmpty
    }
}
