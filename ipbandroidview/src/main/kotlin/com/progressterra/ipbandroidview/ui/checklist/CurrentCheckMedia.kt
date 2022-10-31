package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.dto.Picture
import com.progressterra.ipbandroidview.dto.Voice

data class CurrentCheckMedia(
    val voices: List<Voice>,
    val pictures: List<Picture>
)