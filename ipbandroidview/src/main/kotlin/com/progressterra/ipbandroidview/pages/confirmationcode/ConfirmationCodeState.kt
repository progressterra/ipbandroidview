package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.code.CodeState
import com.progressterra.ipbandroidview.widgets.nextorrepeat.NextOrRepeatState

@Immutable
data class ConfirmationCodeState(
    val code: CodeState = CodeState(),
    val nextOrRepeat: NextOrRepeatState = NextOrRepeatState()
) {

    fun updateCode(newCode: String): ConfirmationCodeState = copy(code = code.updateCode(newCode))

    fun updatePhoneNumber(newPhoneNumber: String): ConfirmationCodeState =
        copy(code = code.updatePhone(newPhoneNumber))

    fun updateCodeEnabled(newEnabled: Boolean): ConfirmationCodeState =
        copy(code = code.updateEnabled(newEnabled))

    fun updateNextEnabled(newEnabled: Boolean): ConfirmationCodeState =
        copy(nextOrRepeat = nextOrRepeat.updateNextEnabled(newEnabled))

    fun updateRepeatEnabled(newEnabled: Boolean): ConfirmationCodeState =
        copy(nextOrRepeat = nextOrRepeat.updateRepeatEnabled(newEnabled))

    fun updateRepeatCount(newCount: String): ConfirmationCodeState =
        copy(nextOrRepeat = nextOrRepeat.updateRepeatCount(newCount))
}