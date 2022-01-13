package com.ivangrod.needlehackpost.infrastructure.post.messaging.dto

import kotlinx.serialization.Serializable

@Serializable
class PostMessage(val title: String, val uri: String, val publicationAt: String)
