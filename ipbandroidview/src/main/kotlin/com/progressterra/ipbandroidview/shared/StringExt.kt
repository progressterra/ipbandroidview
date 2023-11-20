package com.progressterra.ipbandroidview.shared

import androidx.core.util.PatternsCompat

fun String.isEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isRussianPhoneNumber() = matches(Regex("^7\\d{10}$"))

fun String.toDate(): String {
    val stringBuilder = StringBuilder(this)
    listOf(2, 4).sortedDescending().forEach { pos ->
        if (pos <= stringBuilder.length) {
            stringBuilder.insert(pos, ".")
        }
    }
    return stringBuilder.toString()
}

