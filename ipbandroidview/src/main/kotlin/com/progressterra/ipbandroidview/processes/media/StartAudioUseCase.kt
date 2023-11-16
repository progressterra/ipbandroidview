package com.progressterra.ipbandroidview.processes.media

interface StartAudioUseCase {

    suspend operator fun invoke(checkId: String): Result<Unit>

    class Base(
        private val audioManager: AudioManager,
        private val fileExplorer: FileExplorer
    ) : StartAudioUseCase {

        override suspend fun invoke(checkId: String): Result<Unit> = runCatching {
            val path = fileExplorer.audioFile(checkId).path
            audioManager.play(path)
        }
    }
}