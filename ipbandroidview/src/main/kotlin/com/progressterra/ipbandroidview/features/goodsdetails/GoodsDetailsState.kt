package com.progressterra.ipbandroidview.features.goodsdetails

import androidx.compose.runtime.Immutable

@Immutable
data class GoodsDetailsState(
    val name: String,
    val description: String,
    val company: String
)