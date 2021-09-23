package com.ivangrod.needlehackpost.domain.post

interface FeedExtractor {
    fun extract(feed: Feed?): List<Post?>?
}
