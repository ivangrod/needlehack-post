package com.ivangrod.needlehackpost.infrastructure.post.inbound.dto

import java.time.LocalDateTime

class PostDTO(
    val title: String,
    val uri: String,
    val author: String,
    val feedUri: String,
    val feedSource: String,
    val content: String,
    val publicationDate: String,
    val topics: Set<String>
)
