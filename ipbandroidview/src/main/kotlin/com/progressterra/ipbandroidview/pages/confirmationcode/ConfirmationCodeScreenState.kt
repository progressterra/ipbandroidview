package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.features.code.CodeState
import com.progressterra.ipbandroidview.features.countdown.CountDownState


data class ConfirmationCodeScreenState(
    val code: CodeState = CodeState(),
    val repeat: CountDownState = CountDownState()
)