package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileDetailsState(
    val phone: String,
    val name: String,
    val email: String
)
