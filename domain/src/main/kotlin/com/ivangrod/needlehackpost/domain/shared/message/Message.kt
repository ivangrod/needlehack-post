package com.ivangrod.needlehackpost.domain.shared.message

import java.io.Serializable
import java.time.Instant
import java.util.*


abstract class Message {

    private val messageId: String
    private val occurredOn: Instant

    constructor() {
        messageId = UUID.randomUUID().toString()
        occurredOn = Instant.now()
    }

    protected constructor(messageId: String, occurredOn: Instant) {
        this.messageId = messageId
        this.occurredOn = occurredOn
    }

    abstract fun type(): MessageType?
    fun messageId(): String {
        return messageId
    }

    open fun name(): String {
        return name(javaClass)
    }

    fun occurredOn(): Instant {
        return occurredOn
    }

    open fun serialize(): Map<String, Serializable>? {
        return HashMap<String, Serializable>(
            java.util.Map.of(
                "data", HashMap(
                    java.util.Map.of(
                        "id", messageId(),
                        "type", name(),
                        "occurred_on", occurredOn().toString()
                    )
                ),
                "meta", HashMap<Any, Any>()
            )
        )
    }

    companion object {
        fun name(clazz: Class<*>): String {
            return toSnake(
                clazz.name
                    .replace("com.ivangrod", "needlehackpost")
                    .replace(".domain", "")
                    .replace(".model", "")
                    .replace(".command", "")
            ).replace("._", ".")
        }

        @JvmOverloads
        fun simpleName(clazz: Class<*> = javaClass): String {
            return toSnake(clazz.simpleName)
        }

        private fun toSnake(text: String): String {
            return text.camelToSnakeCase()
        }
    }
}

fun String.camelToSnakeCase(): String {
    return "(?<=[a-zA-Z])[A-Z]".toRegex().replace(this) {
        "_${it.value}"
    }.lowercase()
}
