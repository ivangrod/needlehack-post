package com.ivangrod.needlehackpost.infrastructure.shared.runner

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import kotlin.system.exitProcess

abstract class NamedCommandLineRunner : CommandLineRunner {

    @Autowired
    private val context: ConfigurableApplicationContext? = null

    override fun run(vararg args: String) {
        if (args.isNotEmpty() && name() == args[0]) {
            handle()
            exitProcess(SpringApplication.exit(context))
        }
    }

    protected abstract fun handle()
    protected abstract fun name(): String
}
