package com.progressterra.ipbandroidview.core.voice

import android.media.MediaRecorder
import com.progressterra.ipbandroidview.core.FileExplorer

interface VoiceManager {

    fun startRecording(id: String)

    fun stopRecording()

    fun reset()

    class Base(
        private val mediaRecorder: MediaRecorder,
        private val fileExplorer: FileExplorer
    ) : VoiceManager {

        override fun startRecording(id: String) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setOutputFile(fileExplorer.obtainOrCreateAudioFile(id))
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            mediaRecorder.prepare()
            mediaRecorder.start()
        }

        override fun stopRecording() {
            mediaRecorder.stop()
            mediaRecorder.reset()
        }

        override fun reset() {
            runCatching {
                mediaRecorder.reset()
            }
        }
    }
}