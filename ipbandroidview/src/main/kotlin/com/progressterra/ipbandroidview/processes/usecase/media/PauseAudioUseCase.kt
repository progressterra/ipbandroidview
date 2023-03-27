package com.progressterra.ipbandroidview.processes.usecase.media

import com.progressterra.ipbandroidview.core.voice.AudioManager

interface PauseAudioUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        private val audioManager: AudioManager
    ) : PauseAudioUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            audioManager.pause()
        }
    }
}