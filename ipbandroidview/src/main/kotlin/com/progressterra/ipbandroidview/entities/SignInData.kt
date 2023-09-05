package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInData(
    val phone: String = "",
    val token: String = "",
    val allowedAttempts: Int = 0,
    val secondsToResend: Int = 0
) : Parcelable
