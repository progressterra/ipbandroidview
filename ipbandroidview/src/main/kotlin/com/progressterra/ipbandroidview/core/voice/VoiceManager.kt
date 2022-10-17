package com.progressterra.ipbandroidview.core.voice

import android.media.MediaRecorder

interface VoiceManager {

    fun startRecording(checkId: String)

    fun stopRecording()

    fun reset()

    class Base(
        private val mediaRecorder: MediaRecorder,
        private val mediaFiles: MediaFiles
    ) : VoiceManager {

        override fun startRecording(checkId: String) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setOutputFile(mediaFiles.retrieveName(checkId))
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