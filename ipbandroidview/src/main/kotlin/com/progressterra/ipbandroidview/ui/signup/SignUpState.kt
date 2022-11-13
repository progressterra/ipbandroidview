package com.progressterra.ipbandroidview.ui.signup

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class SignUpState(
    val phoneNumber: String = "",
    val name: String = "",
    val email: String = "",
    val birthday: String = "",
    val birthdayDate: LocalDate = LocalDate.now(),
    val isDataValid: Boolean = false,
    val showCalendar: Boolean = false
)
