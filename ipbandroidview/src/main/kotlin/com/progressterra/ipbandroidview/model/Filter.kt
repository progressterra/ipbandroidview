package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable

@Immutable
data class Filter(
    val key: String,
    val values: List<String>
)