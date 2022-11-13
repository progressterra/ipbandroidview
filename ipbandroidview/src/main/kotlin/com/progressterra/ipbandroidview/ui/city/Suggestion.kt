package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Immutable

@Immutable
data class Suggestion(
    val address: String,
    val city: String
)
