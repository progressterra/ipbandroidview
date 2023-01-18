package com.progressterra.ipbandroidview.domain.usecase.media

import com.progressterra.ipbandroidview.core.CreateId
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.model.media.Voice

interface StartRecordingUseCase {

    suspend operator fun invoke(): Result<Voice>

    class Base(
        private val voiceManager: VoiceManager,
        private val fileExplorer: FileExplorer,
        private val createId: CreateId
    ) : StartRecordingUseCase {

        override suspend fun invoke(): Result<Voice> = runCatching {
            val id = createId()
            val path = fileExplorer.audioFile(id).path
            voiceManager.startRecording(path)
            Voice(id = id, local = true, toRemove = false)
        }
    }
}