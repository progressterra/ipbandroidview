package com.progressterra.ipbandroidview.core.voice

interface AudioProgressListener {

    fun progress(progress: Float)

    fun ended()
}