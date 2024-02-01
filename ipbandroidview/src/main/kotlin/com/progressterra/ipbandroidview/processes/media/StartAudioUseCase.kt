package com.progressterra.ipbandroidview.processes.media

interface StartAudioUseCase {

    suspend operator fun invoke(checkId: String)

    class Base(
        private val audioManager: AudioManager,
        private val fileExplorer: FileExplorer
    ) : StartAudioUseCase {

        override suspend fun invoke(checkId: String) {
            val path = fileExplorer.file("$checkId.mp4").absolutePath
            audioManager.play(path)
        }
    }
}