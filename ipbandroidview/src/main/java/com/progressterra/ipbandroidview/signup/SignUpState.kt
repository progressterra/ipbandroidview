package com.progressterra.ipbandroidview.signup

import java.time.LocalDate

data class SignUpState(
    val phoneNumber: String = "",
    val name: String = "",
    val email: String = "",
    val birthday: String = "",
    val birthdayDate: LocalDate = LocalDate.now(),
    val isDataValid: Boolean = false,
    val showCalendar: Boolean = false
)
