package com.progressterra.ipbandroidview.domain.usecase.media

import com.progressterra.ipbandroidview.core.voice.AudioManager

interface AudioProgressUseCase {

    suspend operator fun invoke(): Result<Float>

    class Base(
        private val audioManager: AudioManager
    ) : AudioProgressUseCase {

        override suspend fun invoke(): Result<Float> = runCatching {
            audioManager.progress()
        }
    }
}