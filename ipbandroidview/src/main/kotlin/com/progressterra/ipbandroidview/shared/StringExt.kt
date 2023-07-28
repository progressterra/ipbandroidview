package com.progressterra.ipbandroidview.shared

import androidx.core.util.PatternsCompat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.isEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isNameAndSurname() = matches(Regex("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+\$"))

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

fun String.isDate(): Boolean {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return try {
        format.parse(this)
        true
    } catch (e: ParseException) {
        false
    }
}

fun String.toEpochMillis(): Long {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = format.parse(this)
    return date?.time ?: 0L
}

fun String.splitName(full: Boolean): List<String> {
    val splitName = this.trim().split(" ")
    if (splitName[0].isBlank() || splitName[1].isBlank())
        throw Exception("Bad name")
    if (full)
        if (splitName[2].isBlank())
            throw Exception("Bad name")
    return splitName
}