package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.code.CodeState
import com.progressterra.ipbandroidview.widgets.nextorrepeat.NextOrRepeatState
import com.progressterra.ipbandroidview.widgets.nextorrepeat.uNextEnabled

@Immutable
data class ConfirmationCodeState(
    val code: CodeState = CodeState(),
    val nextOrRepeat: NextOrRepeatState = NextOrRepeatState()
) {

    fun uCode(newCode: String): ConfirmationCodeState = copy(code = code.uCode(newCode))

    fun uPhoneNumber(newPhoneNumber: String): ConfirmationCodeState =
        copy(code = code.uPhone(newPhoneNumber))

    fun uCodeEnabled(newEnabled: Boolean): ConfirmationCodeState =
        copy(code = code.uEnabled(newEnabled))

    fun uNextEnabled(newEnabled: Boolean): ConfirmationCodeState =
        copy(nextOrRepeat = nextOrRepeat.uNextEnabled(newEnabled))

    fun uRepeatEnabled(newEnabled: Boolean): ConfirmationCodeState =
        copy(nextOrRepeat = nextOrRepeat.uRepeatEnabled(newEnabled))

    fun uRepeatCount(newCount: String): ConfirmationCodeState =
        copy(nextOrRepeat = nextOrRepeat.uRepeatCount(newCount))
}