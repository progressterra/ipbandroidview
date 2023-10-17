package com.progressterra.ipbandroidview.features.info

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class InfoState(
    val nickName: TextFieldState = TextFieldState(id = "nickName"),
    val about: TextFieldState = TextFieldState(id = "about")
)