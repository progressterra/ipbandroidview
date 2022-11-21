package com.progressterra.ipbandroidview.ui.confirmationcode

data class ConfirmationCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val timer: String = "",
    val canResend: Boolean = false
)
