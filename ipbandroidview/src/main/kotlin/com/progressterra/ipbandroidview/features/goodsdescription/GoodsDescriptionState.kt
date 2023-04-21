package com.progressterra.ipbandroidview.features.goodsdescription

import androidx.compose.runtime.Immutable

@Immutable
data class GoodsDescriptionState(
    val name: String = "",
    val description: String = "",
    val company: String = ""
)