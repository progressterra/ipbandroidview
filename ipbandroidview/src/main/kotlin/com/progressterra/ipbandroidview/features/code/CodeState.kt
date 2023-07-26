package com.progressterra.ipbandroidview.features.code

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics data class CodeState(
    val code: String = "",
    val phone: String = "",
    val enabled: Boolean = false
) { companion object }
