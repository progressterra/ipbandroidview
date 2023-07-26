package com.progressterra.ipbandroidview.features.countdown

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics data class CountDownState(
    val count: String = "",
    val enabled: Boolean = false
) { companion object }