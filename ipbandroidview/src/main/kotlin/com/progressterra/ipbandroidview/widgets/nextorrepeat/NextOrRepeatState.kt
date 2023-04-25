package com.progressterra.ipbandroidview.widgets.nextorrepeat

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.countdown.CountDownState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.uEnabled
import com.progressterra.processors.IpbSubState

@Immutable
data class NextOrRepeatState(
    @IpbSubState val next: ButtonState = ButtonState(
        id = "next"
    ),
    val repeat: CountDownState = CountDownState()
) {

    fun uNextEnabled(newEnabled: Boolean): NextOrRepeatState =
        copy(next = next.uEnabled(newEnabled))

    fun uRepeatCount(newCount: String): NextOrRepeatState =
        copy(repeat = repeat.uCount(newCount))

    fun uRepeatEnabled(newEnabled: Boolean): NextOrRepeatState =
        copy(repeat = repeat.uEnabled(newEnabled))
}