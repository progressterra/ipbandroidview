package com.progressterra.ipbandroidview.ext

import androidx.core.util.PatternsCompat

fun String.isEmail(): Boolean = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()