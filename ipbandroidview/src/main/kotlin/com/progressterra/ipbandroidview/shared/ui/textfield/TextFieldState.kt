package com.progressterra.ipbandroidview.shared.ui.textfield

import androidx.compose.runtime.Immutable

@Immutable
data class TextFieldState(
    val id: String, val text: String = "", val enabled: Boolean = true
) {

    fun updateText(text: String): TextFieldState = copy(text = text)

    fun updateEnabled(enabled: Boolean): TextFieldState = copy(enabled = enabled)
}