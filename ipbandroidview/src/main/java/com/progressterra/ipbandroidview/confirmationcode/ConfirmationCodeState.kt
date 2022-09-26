package com.progressterra.ipbandroidview.confirmationcode

data class ConfirmationCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val timer: String = "",
    val isTimer: Boolean = false
)
