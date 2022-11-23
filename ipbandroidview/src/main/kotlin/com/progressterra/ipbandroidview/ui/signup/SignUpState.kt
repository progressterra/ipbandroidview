package com.progressterra.ipbandroidview.ui.signup

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import java.time.LocalDate

@Immutable
data class SignUpState(
    val phoneNumber: String = "",
    val name: String = "",
    val email: String = "",
    val birthday: LocalDate = LocalDate.now(),
    val isDataValid: Boolean = false,
    val showCalendar: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
)
