package com.progressterra.ipbandroidview.ui.confirmationcode

import com.progressterra.ipbandroidview.core.ScreenState

data class ConfirmationCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val timer: String = "",
    val canResend: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
)
