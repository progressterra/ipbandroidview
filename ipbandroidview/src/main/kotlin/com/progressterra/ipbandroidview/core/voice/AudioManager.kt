package com.progressterra.ipbandroidview.core.voice

import android.media.MediaPlayer

interface AudioManager {

    fun play(checkId: String, progress: Float)

    fun progress(): Float

    fun pause()

    fun reset()

    class Base(
        private val mediaPlayer: MediaPlayer,
        private val voiceFiles: VoiceFiles
    ) : AudioManager {

        private var lastPreparedCheckId: String? = null

        private fun prepare(checkId: String) {
            if (lastPreparedCheckId != checkId)
                reset()
            mediaPlayer.setDataSource(voiceFiles.retrieveName(checkId))
            mediaPlayer.prepare()
            lastPreparedCheckId = checkId
        }

        override fun play(checkId: String, progress: Float) {
            if (lastPreparedCheckId != checkId)
                prepare(checkId)
            mediaPlayer.start()
        }

        override fun pause() {
            mediaPlayer.pause()
        }

        override fun reset() {
            mediaPlayer.reset()
            lastPreparedCheckId = null
        }

        override fun progress(): Float =
            mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()
    }
}