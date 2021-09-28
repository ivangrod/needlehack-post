package com.ivangrod.needlehackpost.infrastructure.post.runner

import com.ivangrod.needlehackpost.infrastructure.post.service.RssReader
import com.ivangrod.needlehackpost.infrastructure.shared.runner.NamedCommandLineRunner
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CollectPostRunner(private val rssReader: RssReader): NamedCommandLineRunner() {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CollectPostRunner::class.java)
    }

    override fun handle() {
        log.info("Init collecting process:: ${LocalDateTime.now()}")
        rssReader.consumeOpml()
    }

    override fun name(): String {
        return "collect-post-runner"
    }
}
