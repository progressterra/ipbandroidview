package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.CheckPicture
import com.progressterra.ipbandroidview.model.Voice

@Immutable
data class CurrentCheckMedia(
    val voices: List<Voice>,
    val pictures: List<CheckPicture>
)