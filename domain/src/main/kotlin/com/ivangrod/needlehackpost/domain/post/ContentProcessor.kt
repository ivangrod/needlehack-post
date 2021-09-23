package com.ivangrod.needlehackpost.domain.post

interface ContentProcessor {
    fun execute(contentWithoutProcessing: String?): String?
}
