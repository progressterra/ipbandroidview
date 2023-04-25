package com.progressterra.ipbandroidview.shared.ui.textfield

import androidx.compose.runtime.Immutable

@Immutable
data class TextFieldState(
    val id: String = "",
    val text: String = "",
    val enabled: Boolean = true,
    val valid: Boolean = true
) {

    fun uText(text: String): TextFieldState = copy(text = text)

    fun uEnabled(enabled: Boolean): TextFieldState = copy(enabled = enabled)

    fun uValid(valid: Boolean): TextFieldState = copy(valid = valid)
}