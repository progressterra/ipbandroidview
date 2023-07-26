package com.progressterra.ipbandroidview.features.authprofile

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics
data class AuthProfileState(
    val profileImage: String = "",
    val name: String = "",
    val email: String = "",
) { companion object }