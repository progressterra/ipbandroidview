package com.progressterra.ipbandroidview.processes.media

import com.progressterra.ipbandroidview.processes.utils.CreateId
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
            val path = fileExplorer.file("$id.mp4").absolutePath
            voiceManager.startRecording(path)
            Voice(id = id, local = true, toRemove = false)
        }
    }
}