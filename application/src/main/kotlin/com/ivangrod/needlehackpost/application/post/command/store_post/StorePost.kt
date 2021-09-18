package com.ivangrod.needlehackpost.application.post.command.store_post

import com.ivangrod.needlehackpost.domain.shared.message.Command
import java.util.*

class StorePost : Command {

    private val title: String
    private val link: String

    constructor(postId: UUID, title: String, link: String) : super(postId) {
        this.title = title
        this.link = link
    }
}
