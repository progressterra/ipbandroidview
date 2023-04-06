package com.progressterra.ipbandroidview.processes.usecase.media

import com.progressterra.ipbandroidview.shared.voice.VoiceManager

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