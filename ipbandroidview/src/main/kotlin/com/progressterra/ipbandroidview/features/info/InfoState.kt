package com.progressterra.ipbandroidview.features.info

import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState


data class InfoState(
    val nickName: TextFieldState = TextFieldState(id = "nickName"),
    val about: TextFieldState = TextFieldState(id = "about"),
    val profession: TextFieldState = TextFieldState(id = "profession")
)