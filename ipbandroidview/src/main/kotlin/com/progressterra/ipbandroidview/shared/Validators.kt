package com.progressterra.ipbandroidview.shared

import androidx.core.util.PatternsCompat

fun String.isEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isNameAndSurname() = matches(Regex("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+\$"))

fun String.isRussianPhoneNumber() = matches(Regex("^7\\d{10}$"))


fun String.isTestPhoneNumber() = this == "1777555777"

