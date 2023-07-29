package com.progressterra.ipbandroidview.features.code

import androidx.compose.runtime.Immutable

@Immutable
data class CodeState(
    val code: String = "",
    val phone: String = "",
    val enabled: Boolean = false
)
