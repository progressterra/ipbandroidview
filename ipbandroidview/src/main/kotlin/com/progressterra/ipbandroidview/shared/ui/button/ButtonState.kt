package com.progressterra.ipbandroidview.shared.ui.button

data class ButtonState(
    val id: String = "",
    val text: String = "",
    val enabled: Boolean = true
) {

    fun updateText(text: String): ButtonState = copy(text = text)

    fun updateEnabled(enabled: Boolean): ButtonState = copy(enabled = enabled)
}