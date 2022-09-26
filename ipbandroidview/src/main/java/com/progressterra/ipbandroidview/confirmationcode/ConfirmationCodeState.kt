package com.progressterra.ipbandroidview.confirmationcode

import androidx.compose.runtime.Immutable

data class ConfirmationCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val timer: String = "",
    val isTimer: Boolean = false
)
