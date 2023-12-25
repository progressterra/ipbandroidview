package com.progressterra.ipbandroidview.processes.media

interface AudioProgressUseCase {

    suspend operator fun invoke(): Float

    class Base(
        private val audioManager: AudioManager
    ) : AudioProgressUseCase {

        override suspend fun invoke() = audioManager.progress()

    }
}