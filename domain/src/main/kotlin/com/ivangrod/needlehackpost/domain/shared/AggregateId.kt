package com.ivangrod.needlehackpost.domain.shared

import java.util.*


abstract class AggregateId : Id {

    protected constructor() : super()
    protected constructor(id: UUID?) : super(id)
}
