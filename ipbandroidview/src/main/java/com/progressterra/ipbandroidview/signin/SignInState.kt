package com.progressterra.ipbandroidview.signin

import androidx.compose.runtime.Immutable

data class SignInState(
    val phoneNumber: String = "",
    val isDataValid: Boolean = false
)
