package com.progressterra.ipbandroidview.features.proshkaoffer

import androidx.compose.runtime.Immutable

@Immutable
data class ProshkaOfferState(
    val id: String = "",
    val title: String = "",
    val image: String = ""
)