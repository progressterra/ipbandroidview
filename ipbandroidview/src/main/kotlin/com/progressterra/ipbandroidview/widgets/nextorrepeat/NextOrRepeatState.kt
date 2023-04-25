package com.progressterra.ipbandroidview.widgets.nextorrepeat

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.countdown.CountDownState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class NextOrRepeatState(
    val next: ButtonState = ButtonState(
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