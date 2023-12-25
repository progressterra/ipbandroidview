package com.progressterra.ipbandroidview.processes.media

import android.media.MediaPlayer
import com.progressterra.ipbandroidview.shared.log

interface AudioManager {

    fun play(path: String)

    fun progress(): Float

    fun pause()

    class Base(
        private val mediaPlayer: MediaPlayer
    ) : AudioManager {

        private var lastPreparedCheckPath: String? = null

        override fun play(path: String) {
            log("AUDIO", "play $path")
            if (lastPreparedCheckPath != path) {
                lastPreparedCheckPath = path
                mediaPlayer.reset()
                mediaPlayer.setDataSource(path)
                mediaPlayer.prepare()
            }
            mediaPlayer.start()
        }

        override fun pause() {
            log("AUDIO", "pause")
            mediaPlayer.pause()
        }

        override fun progress(): Float =
            mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration
    }
}