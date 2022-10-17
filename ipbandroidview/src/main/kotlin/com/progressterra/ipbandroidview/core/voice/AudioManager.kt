package com.progressterra.ipbandroidview.core.voice

import android.media.MediaPlayer

interface AudioManager {

    fun startPlay(checkId: String, progress: Float)

    fun progress(): Float

    fun stopPlay()

    fun endPlay()

    class Base(
        private val mediaPlayer: MediaPlayer,
        private val createName: CreateName
    ) : AudioManager {

        private var lastPreparedCheckId: String? = null

        private fun prepare(checkId: String) {
            if (lastPreparedCheckId != checkId)
                endPlay()
            mediaPlayer.setDataSource(createName.audioFileForCheckId(checkId))
            mediaPlayer.prepare()
            lastPreparedCheckId = checkId
        }

        override fun startPlay(checkId: String, progress: Float) {
            if (lastPreparedCheckId != checkId)
                prepare(checkId)
            mediaPlayer.seekTo((mediaPlayer.duration * progress).toInt())
            mediaPlayer.start()
        }

        override fun stopPlay() {
            mediaPlayer.pause()
        }

        override fun endPlay() {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }

        override fun progress(): Float =
            (mediaPlayer.duration / mediaPlayer.currentPosition).toFloat()
    }
}