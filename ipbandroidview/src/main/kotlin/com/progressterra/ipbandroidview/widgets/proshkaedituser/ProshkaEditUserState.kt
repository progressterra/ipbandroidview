package com.progressterra.ipbandroidview.widgets.proshkaedituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.TextFieldState

@Immutable
data class ProshkaEditUserState(
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val phone: TextFieldState = TextFieldState(),
    val birthday: TextFieldState = TextFieldState(),
    val citizenship: TextFieldState = TextFieldState(),
    val address: TextFieldState = TextFieldState(),
    val passport: TextFieldState = TextFieldState(),
    val passportProvider: TextFieldState = TextFieldState(),
    val passportProviderCode: TextFieldState = TextFieldState(),
    val patent: TextFieldState = TextFieldState()
)