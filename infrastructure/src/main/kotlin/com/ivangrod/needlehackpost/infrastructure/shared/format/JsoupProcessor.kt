package com.ivangrod.needlehackpost.infrastructure.shared.format

import com.ivangrod.needlehackpost.domain.post.ContentProcessor
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Whitelist


class JsoupProcessor : ContentProcessor {

    override fun execute(contentWithoutProcessing: String?): String? {
        return StringEscapeUtils.unescapeHtml4(
            Jsoup
                .clean(
                    contentWithoutProcessing?:"", "", Whitelist.none(),
                    Document.OutputSettings().prettyPrint(false)
                )
        )
    }
}
