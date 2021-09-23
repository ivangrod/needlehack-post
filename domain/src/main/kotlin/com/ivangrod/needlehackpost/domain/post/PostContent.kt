package com.ivangrod.needlehackpost.domain.post

class PostContent private constructor(val value: String?) {
    companion object {
        fun buildWithContentProcessed(
            contentWithoutProcessing: String?,
            processor: ContentProcessor
        ): PostContent {
            return PostContent(processor.execute(contentWithoutProcessing))
        }

        fun buildWithContentPlain(contentWithoutProcessing: String?): PostContent {
            return PostContent(contentWithoutProcessing)
        }
    }
}
