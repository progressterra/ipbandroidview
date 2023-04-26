package com.progressterra.ipbandroidview.features.ordercard

import androidx.compose.runtime.Immutable

@Immutable
data class OrderCardState(
    val id: String,
    val status: String
)