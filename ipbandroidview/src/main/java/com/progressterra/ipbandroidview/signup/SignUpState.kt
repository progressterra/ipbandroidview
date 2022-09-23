package com.progressterra.ipbandroidview.signup

data class SignUpState(
    val phoneNumber: String = "",
    val name: String = "",
    val email: String = "",
    val birthday: String = "",
    val isDataValid: Boolean = false
)
