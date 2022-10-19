package com.progressterra.ipbandroidview.core.voice

import android.content.Context

interface VoiceFiles {

    fun retrieveName(checkId: String): String

    class Base(
        private val context: Context
    ) : VoiceFiles {

        override fun retrieveName(checkId: String): String =
            "${context.filesDir.path}/Voice message for check with id $checkId"
    }
}