package com.ivangrod.needlehackpost.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class PostTest {

    @Test
    fun should_return_link() {
        assertThat(
            Post(
                UUID.fromString("89af1fef-58e8-4b3c-ab01-ee7ee31b7872"),
                "Google",
                "http://www.google.com"
            ).uri
        ).isNotEmpty
    }
}
