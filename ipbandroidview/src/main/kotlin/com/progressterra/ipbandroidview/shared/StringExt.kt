package com.progressterra.ipbandroidview.shared

import androidx.core.util.PatternsCompat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.isEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isNameAndSurname() = matches(Regex("^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+\$"))

fun String.isRussianPhoneNumber() = matches(Regex("^7\\d{10}$"))

fun String.isTestPhoneNumber() = this == "1777555777"

fun String.isBirthday(): Boolean {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return try {
        LocalDate.parse(this, formatter)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

fun String.toEpochMillis(): Long {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = LocalDate.parse(this, formatter)
    return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
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

data class Address(
    val city: String,
    val street: String,
    val building: String,
    val apartment: String?
)

fun String.isAddress() =
    matches(Regex("^(г\\.[^,]+|д\\.[^,]+|пгт\\.[^,]+|с\\.[^,]+), ул\\.[^,]+, д\\.[^,]+(, кв\\.[^,]+)?$"))

fun String.toAddress(): Address {
    val parts = this.split(", ")
    val city = parts.getOrNull(0)?.substringAfter("г.")?.takeIf { it.isNotBlank() }
        ?: parts.getOrNull(0)?.substringAfter("д.")?.takeIf { it.isNotBlank() }
        ?: parts.getOrNull(0)?.substringAfter("пгт.")?.takeIf { it.isNotBlank() }
        ?: parts.getOrNull(0)?.substringAfter("с.")?.takeIf { it.isNotBlank() } ?: ""
    val street = parts.firstOrNull { it.startsWith("ул.") }?.substringAfter("ул.")?.trim() ?: ""
    val building = parts.firstOrNull { it.startsWith("д.") }?.substringAfter("д.")?.trim() ?: ""
    val apartment = parts.firstOrNull { it.startsWith("кв.") }?.substringAfter("кв.")?.trim()
    return Address(city, street, building, apartment)
}

fun String.isValidCitizenship() = this == "Россия"
