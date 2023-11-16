package com.progressterra.ipbandroidview.processes.media

interface StopRecordingUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        private val voiceManager: VoiceManager
    ) : StopRecordingUseCase {

        override suspend fun invoke(): Result<Unit> = runCatching {
            voiceManager.stopRecording()
        }
    }
}