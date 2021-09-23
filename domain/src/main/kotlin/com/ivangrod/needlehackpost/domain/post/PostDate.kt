package com.ivangrod.needlehackpost.domain.post

import com.ivangrod.needlehackpost.domain.post.Feed
import com.ivangrod.needlehackpost.domain.post.Post
import com.ivangrod.needlehackpost.domain.post.ContentProcessor
import com.ivangrod.needlehackpost.domain.post.PostContent
import java.time.LocalDateTime
import java.time.ZoneId
import com.ivangrod.needlehackpost.domain.post.PostDate
import java.time.format.DateTimeFormatter
import java.util.*

class PostDate {
    private val collectAt: LocalDateTime
    val publicationAt: LocalDateTime

    constructor(publicationAt: Date) {
        collectAt = LocalDateTime.now()
        this.publicationAt = publicationAt.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    constructor(publicationAt: LocalDateTime) {
        collectAt = LocalDateTime.now()
        this.publicationAt = publicationAt
    }

    constructor(collectAt: LocalDateTime, publicationAt: LocalDateTime) {
        this.collectAt = collectAt
        this.publicationAt = publicationAt
    }

    fun publicationDateToStringFormat(): String {
        return publicationAt.format(TIME_FORMATTER)
    }

    companion object {
        private val TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm")

        fun buildStringToPublicationDate(dateTime: String?): PostDate {
            return PostDate(LocalDateTime.parse(dateTime, TIME_FORMATTER))
        }
    }
}
