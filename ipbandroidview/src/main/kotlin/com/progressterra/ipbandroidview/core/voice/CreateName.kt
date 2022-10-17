package com.progressterra.ipbandroidview.core.voice

interface CreateName {

    fun audioFileForCheckId(checkId: String): String

    class Base : CreateName {

        override fun audioFileForCheckId(checkId: String): String =
            "Voice message for check with id $checkId"
    }
}