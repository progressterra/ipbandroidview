package com.progressterra.ipbandroidview.features.profilebutton

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileButtonState(
    val id: String = "",
    val title: String = "",
    val isDanger: Boolean = false
)