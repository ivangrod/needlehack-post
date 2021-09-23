package com.ivangrod.needlehackpost.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class PostTest {

    @Test
    fun should_return_link() {
        assertThat(
            Post.create(
                PostTitle("Google"),
                PostUri("http://www.google.com"),
                Author("Larry Page"),
                Feed("http://www.google.com", "Google"),
                PostContent.buildWithContentPlain(""),
                PostDate(LocalDateTime.now()),
                emptySet()
            ).uri
        ).isNotNull
    }
}
