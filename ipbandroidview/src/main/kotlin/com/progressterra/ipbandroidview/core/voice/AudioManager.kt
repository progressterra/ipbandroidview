package com.progressterra.ipbandroidview.core.voice

import android.media.MediaPlayer

interface AudioManager {

    fun setListener(listener: AudioProgressListener)

    fun startPlay(checkId: String, progress: Float)

    fun progress(): Float

    fun stopPlay()

    fun reset()

    class Base(
        private val mediaPlayer: MediaPlayer,
        private val mediaFiles: MediaFiles
    ) : AudioManager {

        private var lastPreparedCheckId: String? = null
        private var listener: AudioProgressListener? = null

        private fun prepare(checkId: String) {
            if (lastPreparedCheckId != checkId)
                reset()
            mediaPlayer.setDataSource(mediaFiles.retrieveName(checkId))
            mediaPlayer.prepare()
            lastPreparedCheckId = checkId
        }

        override fun startPlay(checkId: String, progress: Float) {
            if (lastPreparedCheckId != checkId)
                prepare(checkId)
            mediaPlayer.seekTo((mediaPlayer.duration * progress).toInt())
            mediaPlayer.start()
            mediaPlayer.setOnBufferingUpdateListener { _, percent ->
                listener?.progress(percent.toFloat() / 100f)
                if (percent == 100)
                    listener?.ended()
            }
        }

        override fun stopPlay() {
            mediaPlayer.stop()
            reset()
        }

        override fun reset() {
            mediaPlayer.reset()
        }

        override fun progress(): Float =
            mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()

        override fun setListener(listener: AudioProgressListener) {
            this.listener = listener
        }
    }
}