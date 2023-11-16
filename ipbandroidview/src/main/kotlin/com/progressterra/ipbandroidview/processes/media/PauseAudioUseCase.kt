package com.progressterra.ipbandroidview.processes.media

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