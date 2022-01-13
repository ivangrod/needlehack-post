package com.ivangrod.needlehackpost.domain.post

interface SenderPost {

    fun send(title: PostTitle, uri: PostUri, publishedDate: PostDate)
}
