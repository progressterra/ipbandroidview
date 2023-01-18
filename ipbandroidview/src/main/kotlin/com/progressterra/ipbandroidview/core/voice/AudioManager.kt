package com.progressterra.ipbandroidview.core.voice

import android.media.MediaPlayer

interface AudioManager {

    fun play(path: String)

    fun progress(): Float

    fun pause()

    class Base(
        private val mediaPlayer: MediaPlayer
    ) : AudioManager {

        private var lastPreparedCheckPath: String? = null

        override fun play(path: String) {
            if (lastPreparedCheckPath != path) {
                lastPreparedCheckPath = path
                mediaPlayer.reset()
                mediaPlayer.setDataSource(path)
                mediaPlayer.prepare()
            }
            mediaPlayer.start()
        }

        override fun pause() {
            mediaPlayer.pause()
        }

        override fun progress(): Float =
            mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration
    }
}