package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.core.Picture
import com.progressterra.ipbandroidview.core.voice.Voice

data class CurrentCheckMedia(
    val voices: List<Voice>,
    val pictures: List<Picture>
)