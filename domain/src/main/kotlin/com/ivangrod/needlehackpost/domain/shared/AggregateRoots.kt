package com.ivangrod.needlehackpost.domain.shared

import com.ivangrod.needlehackpost.domain.shared.exception.AggregateRootDoesNotExist

interface AggregateRoots<T : AggregateRoot?, K : AggregateId?> {

    @Throws(AggregateRootDoesNotExist::class)
    operator fun get(id: K): T

    fun save(root: T)

    fun exist(id: K): Boolean

    fun remove(root: T)
}
