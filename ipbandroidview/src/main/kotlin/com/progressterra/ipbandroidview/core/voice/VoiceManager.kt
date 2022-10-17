package com.progressterra.ipbandroidview.core.voice

import android.media.MediaRecorder

interface VoiceManager {

    fun startRecording(checkId: String)

    fun stopRecording()

    class Base(
        private val mediaRecorder: MediaRecorder,
        private val createName: CreateName
    ) : VoiceManager {


        override fun startRecording(checkId: String) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setOutputFile(createName.audioFileForCheckId(checkId))
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            mediaRecorder.prepare()
            mediaRecorder.start()
        }

        override fun stopRecording() {
            mediaRecorder.stop()
            mediaRecorder.reset()
        }
    }
}