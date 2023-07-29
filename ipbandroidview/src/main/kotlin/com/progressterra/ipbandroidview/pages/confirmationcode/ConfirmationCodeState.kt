package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.code.CodeState
import com.progressterra.ipbandroidview.features.countdown.CountDownState

@Immutable
data class ConfirmationCodeState(
    val code: CodeState = CodeState(),
    val repeat: CountDownState = CountDownState()
)