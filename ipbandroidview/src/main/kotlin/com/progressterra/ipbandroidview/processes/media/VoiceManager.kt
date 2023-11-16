package com.progressterra.ipbandroidview.processes.media

import android.media.MediaRecorder

interface VoiceManager {

    fun startRecording(path: String)

    fun stopRecording()

    class Base(
        private val mediaRecorder: MediaRecorder,
    ) : VoiceManager {

        private var isPrepared: Boolean = false

        override fun startRecording(path: String) {
            if (isPrepared) {
                mediaRecorder.reset()
            }
            isPrepared = true
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setOutputFile(path)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            mediaRecorder.prepare()
            mediaRecorder.start()
        }

        override fun stopRecording() {
            mediaRecorder.stop()
        }
    }
}