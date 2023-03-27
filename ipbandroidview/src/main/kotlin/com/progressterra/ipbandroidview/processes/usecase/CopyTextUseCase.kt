package com.progressterra.ipbandroidview.processes.usecase

import android.content.ClipData
import android.content.ClipboardManager

interface CopyTextUseCase {

    suspend operator fun invoke(text: String)

    class Base(
        private val clipboardManager: ClipboardManager
    ) : CopyTextUseCase {

        override suspend fun invoke(text: String) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("invite text", text))
        }
    }
}