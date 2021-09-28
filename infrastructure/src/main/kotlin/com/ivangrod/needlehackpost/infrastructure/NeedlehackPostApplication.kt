package com.ivangrod.needlehackpost.infrastructure

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class NeedlehackPostApplication

fun main(args:Array<String>) {
    SpringApplication.run(NeedlehackPostApplication::class.java,*args)
}
