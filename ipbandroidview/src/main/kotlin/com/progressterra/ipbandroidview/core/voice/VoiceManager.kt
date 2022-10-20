package com.progressterra.ipbandroidview.core.voice

import android.media.MediaRecorder
import com.progressterra.ipbandroidview.core.FileExplorer

interface VoiceManager {

    fun startRecording(checkId: String)

    fun stopRecording()

    fun reset()

    class Base(
        private val mediaRecorder: MediaRecorder,
        private val fileExplorer: FileExplorer
    ) : VoiceManager {

        override fun startRecording(checkId: String) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setOutputFile(fileExplorer.obtainOrCreateAudioFile(checkId))
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            mediaRecorder.prepare()
            mediaRecorder.start()
        }

        override fun stopRecording() {
            mediaRecorder.stop()
            reset()
        }

        override fun reset() {
            mediaRecorder.reset()
        }
    }
}