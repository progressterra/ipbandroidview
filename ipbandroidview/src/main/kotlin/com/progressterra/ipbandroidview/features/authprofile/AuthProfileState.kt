package com.progressterra.ipbandroidview.features.authprofile

import androidx.compose.runtime.Immutable

@Immutable
data class AuthProfileState(
    val profileImage: String = "",
    val name: String = "",
    val email: String = "",
)