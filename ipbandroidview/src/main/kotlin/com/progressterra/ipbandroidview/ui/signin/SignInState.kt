package com.progressterra.ipbandroidview.ui.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInState(
    val phoneNumber: String = "",
    val isDataValid: Boolean = false
)
