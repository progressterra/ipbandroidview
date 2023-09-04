package com.progressterra.ipbandroidview.entities

data class SignInData(
    val phone: String = "",
    val token: String = "",
    val allowedAttempts: Int = 0,
    val secondsToResend: Int = 0
)
