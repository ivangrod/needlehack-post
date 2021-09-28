package com.ivangrod.needlehackpost.infrastructure.post.service

import com.ivangrod.needlehackpost.domain.post.Feed
import com.ivangrod.needlehackpost.infrastructure.NeedlehackPostApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [NeedlehackPostApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RssFeedExtractorShould {

    @Autowired
    private val rssFeedExtractor: RssFeedExtractor? = null

    @Test
    fun return_a_collection_of_posts_given_a_feed_with_an_rss_uri() {
        val posts = rssFeedExtractor?.extract(Feed("https://developers.500px.com/feed", "500px"))
        posts?.let { assertThat(it).isNotEmpty }
    }
}

