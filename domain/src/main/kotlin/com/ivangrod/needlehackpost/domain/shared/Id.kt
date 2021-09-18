package com.ivangrod.needlehackpost.domain.shared

import java.util.*


abstract class Id protected constructor(id: String = UUID.randomUUID().toString()) {

    private val id: String

    protected constructor(id: UUID?) : this(id?.toString()!!) {}

    fun id(): String {
        return id
    }

    fun uuid(): UUID {
        return UUID.fromString(id)
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        return if (o == null || javaClass != o.javaClass) false else id == (o as Id).id
    }

    override fun toString(): String {
        return id
    }

    /*
    * Companion objects are singletons defined within a class
    */
    companion object {
        /*
        * This method doesn’t belong to any instance; it’s part of the class
        */
        private fun ensureValidUUID(value: String?) {
            requireNotNull(value) { "Invalid UUID as input" }
            UUID.fromString(value)
        }
    }

    init {
        ensureValidUUID(id)
        this.id = id
    }
}
