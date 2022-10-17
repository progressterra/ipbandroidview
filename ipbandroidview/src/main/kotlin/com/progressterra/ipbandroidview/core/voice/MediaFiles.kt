package com.progressterra.ipbandroidview.core.voice

interface MediaFiles {

    fun retrieveName(checkId: String): String

    class Base : MediaFiles {

        override fun retrieveName(checkId: String): String =
            "Voice message for check with id $checkId"
    }
}