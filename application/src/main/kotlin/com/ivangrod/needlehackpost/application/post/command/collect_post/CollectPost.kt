package com.ivangrod.needlehackpost.application.post.command.collect_post

import com.ivangrod.needlehackpost.domain.post.Feed

class CollectPost(private val feedUri: String, private val source: String) {

    fun createFeed(): Feed {
        return Feed(feedUri, source)
    }
}
