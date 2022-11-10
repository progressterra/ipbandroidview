package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.model.CheckPicture
import com.progressterra.ipbandroidview.model.Voice

data class CurrentCheckMedia(
    val voices: List<Voice>,
    val pictures: List<CheckPicture>
)