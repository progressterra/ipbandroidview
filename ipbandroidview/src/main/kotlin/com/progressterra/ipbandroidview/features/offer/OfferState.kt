package com.progressterra.ipbandroidview.features.offer

import androidx.compose.runtime.Immutable

@Immutable
data class OfferState(
    val title: String = "",
    val image: String = ""
)