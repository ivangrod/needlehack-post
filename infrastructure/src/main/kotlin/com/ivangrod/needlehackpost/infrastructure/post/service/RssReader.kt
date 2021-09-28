package com.ivangrod.needlehackpost.infrastructure.post.service

import com.ivangrod.needlehackpost.application.post.command.collect_post.CollectPost
import com.ivangrod.needlehackpost.application.post.command.collect_post.CollectPostHandler
import com.rometools.opml.feed.opml.Opml
import com.rometools.opml.feed.opml.Outline
import com.rometools.rome.io.FeedException
import com.rometools.rome.io.WireFeedInput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.util.CollectionUtils
import org.xml.sax.InputSource
import java.io.IOException
import java.util.*
import java.util.function.Consumer


class RssReader(
   resourceOpml: Resource,
    collectorPostHandler: CollectPostHandler
) {
    private val resourceOpml: Resource
    private val collectorPostHandler: CollectPostHandler

    fun consumeOpml() {
        val input = WireFeedInput()
        var outlines: List<Outline>? = null
        try {
            val feed = input.build(InputSource(resourceOpml.inputStream)) as Opml
            outlines = feed.outlines
        } catch (exc: Exception) {
            when (exc) {
                is IllegalArgumentException, is IOException, is FeedException -> {
                    logger.error("An error has been produced while opml was processed", exc)
                }
                else -> throw exc
            }
        }
        val outlinesToRead =
            if (CollectionUtils.isEmpty(outlines)) Collections.emptyList() else outlines!![0].children
        outlinesToRead
            .forEach(Consumer { outlineForFeed: Outline ->
                // TODO Extract the invocation of use case from here
                logger.info("Executing collector post use case ${outlineForFeed.xmlUrl}")
                collectorPostHandler.execute(
                    CollectPost(outlineForFeed.xmlUrl, outlineForFeed.text)
                )
            })
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(RssReader::class.java)
    }

    init {
        this.resourceOpml = resourceOpml
        this.collectorPostHandler = collectorPostHandler
    }
}

