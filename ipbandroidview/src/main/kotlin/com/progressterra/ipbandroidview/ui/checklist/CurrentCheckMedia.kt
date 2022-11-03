package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.dto.CheckPicture
import com.progressterra.ipbandroidview.dto.Voice

data class CurrentCheckMedia(
    val voices: List<Voice>,
    val pictures: List<CheckPicture>
)