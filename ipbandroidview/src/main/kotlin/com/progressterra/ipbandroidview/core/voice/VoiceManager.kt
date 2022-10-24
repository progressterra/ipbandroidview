package com.progressterra.ipbandroidview.core.voice

import android.media.MediaRecorder
import java.io.File

interface VoiceManager {

    fun startRecording(file: File)

    fun stopRecording()

    fun reset()

    class Base(
        private val mediaRecorder: MediaRecorder,
    ) : VoiceManager {

        override fun startRecording(file: File) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setOutputFile(file)
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