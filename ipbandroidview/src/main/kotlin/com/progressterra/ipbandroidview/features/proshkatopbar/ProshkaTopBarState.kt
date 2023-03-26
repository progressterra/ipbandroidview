package com.progressterra.ipbandroidview.features.proshkatopbar

import androidx.compose.runtime.Immutable

@Immutable
data class ProshkaTopBarState(
    val id: String = "", val title: String = "", val showBackButton: Boolean = false
)