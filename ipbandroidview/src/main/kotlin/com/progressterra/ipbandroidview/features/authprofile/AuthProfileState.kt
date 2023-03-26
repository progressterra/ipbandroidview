package com.progressterra.ipbandroidview.features.authprofile

import androidx.compose.runtime.Immutable

@Immutable
data class AuthProfileState(
    val id: String = "",
    val profileImage: String = ""
)