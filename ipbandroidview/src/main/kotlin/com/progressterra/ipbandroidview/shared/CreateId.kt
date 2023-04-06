package com.progressterra.ipbandroidview.shared

import java.util.UUID

interface CreateId {

    operator fun invoke(): String

    class Base : CreateId {

        override fun invoke(): String = UUID.randomUUID().toString()
    }
}