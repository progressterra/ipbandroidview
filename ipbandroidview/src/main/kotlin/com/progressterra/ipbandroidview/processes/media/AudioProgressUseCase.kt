package com.progressterra.ipbandroidview.processes.media

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