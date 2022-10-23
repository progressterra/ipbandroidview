package com.progressterra.ipbandroidview.core.voice

import android.media.MediaPlayer
import com.progressterra.ipbandroidview.core.FileExplorer

interface AudioManager {

    fun play(id: String)

    fun progress(): Float

    fun pause()

    fun reset()

    class Base(
        private val mediaPlayer: MediaPlayer,
        private val fileExplorer: FileExplorer
    ) : AudioManager {

        private var lastPreparedCheckId: String? = null

        private fun prepare(id: String) {
            if (lastPreparedCheckId != null)
                reset()
            mediaPlayer.setDataSource(fileExplorer.audioFile(id).path)
            mediaPlayer.prepare()
            lastPreparedCheckId = id
        }

        override fun play(id: String) {
            if (lastPreparedCheckId != id)
                prepare(id)
            mediaPlayer.start()
        }

        override fun pause() {
            mediaPlayer.pause()
        }

        override fun reset() {
            runCatching {
                lastPreparedCheckId = null
                mediaPlayer.reset()
            }
        }

        override fun progress(): Float = mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat()
    }
}