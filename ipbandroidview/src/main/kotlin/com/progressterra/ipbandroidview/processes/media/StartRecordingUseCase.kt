package com.progressterra.ipbandroidview.processes.media

import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.voice.VoiceManager
import com.progressterra.ipbandroidview.entities.Voice

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