package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.compose.runtime.Immutable

@Immutable
data class ConfirmationCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val timer: String = "",
    val isTimer: Boolean = false
)
