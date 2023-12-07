package com.progressterra.ipbandroidview.pages.confirmationcode

import com.progressterra.ipbandroidview.entities.SignInData
import com.progressterra.ipbandroidview.features.code.CodeState
import com.progressterra.ipbandroidview.features.countdown.CountDownState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState


data class ConfirmationCodeScreenState(
    val code: CodeState = CodeState(),
    val skip: ButtonState = ButtonState(id = "skip"),
    val signInData: SignInData = SignInData(),
    val repeat: CountDownState = CountDownState()
)