package com.ivangrod.needlehackpost.domain.post

import com.ivangrod.needlehackpost.domain.shared.AggregateId
import com.ivangrod.needlehackpost.domain.shared.AggregateRoot

class Post(private val postId: PostId, val title: String, val uri: String) : AggregateRoot() {

    override fun id(): AggregateId? = postId
}
