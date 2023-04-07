package com.progressterra.ipbandroidview.widgets.nextorrepeat

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.countdown.CountDownState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class NextOrRepeatState(
    val next: ButtonState = ButtonState(),
    val repeat: CountDownState = CountDownState()
) {

    fun updateNextEnabled(newEnabled: Boolean): NextOrRepeatState =
        copy(next = next.updateEnabled(newEnabled))

    fun updateRepeatCount(newCount: String): NextOrRepeatState =
        copy(repeat = repeat.updateCount(newCount))

    fun updateRepeatEnabled(newEnabled: Boolean): NextOrRepeatState =
        copy(repeat = repeat.updateEnabled(newEnabled))
}