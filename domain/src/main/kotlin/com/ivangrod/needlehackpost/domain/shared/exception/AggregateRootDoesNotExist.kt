package com.ivangrod.needlehackpost.domain.shared.exception

import com.ivangrod.needlehackpost.domain.shared.AggregateId

class AggregateRootDoesNotExist(rootClass: Class<*>, id: AggregateId) : RuntimeException(
    "It does not exist any ${rootClass.simpleName} with the ${id.id()} id given"
) {
    companion object {
        private const val serialVersionUID = -1348782113922199384L
    }
}
