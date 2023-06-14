package com.progressterra.ipbandroidview.features.info

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class InfoState(
    val about: TextFieldState = TextFieldState(id = "about"),
    val profession: TextFieldState = TextFieldState(id = "profession")
)