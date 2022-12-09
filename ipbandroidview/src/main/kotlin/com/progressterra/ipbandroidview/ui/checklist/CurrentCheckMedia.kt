package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.media.MultisizedImage
import com.progressterra.ipbandroidview.model.media.Voice

@Immutable
data class CurrentCheckMedia(
    val voices: List<Voice>,
    val pictures: List<MultisizedImage>
)