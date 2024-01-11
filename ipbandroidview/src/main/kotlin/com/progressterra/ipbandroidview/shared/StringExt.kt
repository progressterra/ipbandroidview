package com.progressterra.ipbandroidview.shared

import androidx.core.util.PatternsCompat

/**
 * Ext fun that checks if string is email
 */
fun String.isEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

/**
 * Ext fun that checks if string is phone number
 */
fun String.isRussianPhoneNumber() = matches(Regex("^7\\d{10}$"))

/**
 * Ext fun that converts string to date format. Example: 01012020 -> 01.01.2020
 */
fun String.toDate(): String {
    val stringBuilder = StringBuilder(this)
    listOf(2, 4).sortedDescending().forEach { pos ->
        if (pos <= stringBuilder.length) {
            stringBuilder.insert(pos, ".")
        }
    }
    return stringBuilder.toString()
}

