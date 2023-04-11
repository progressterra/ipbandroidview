package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.entities.Id
import java.time.LocalDate

fun <T : Id> List<T>.replaceById(item: T): List<T> =
    this.toMutableList().apply { this[indexOfFirst { item.id == it.id }] = item }

fun LocalDate.print(): String = "${this.dayOfMonth}.${this.monthValue}.${this.year}"

fun <T> Result<T>.throwOnFailure() {
    this.onFailure { throw it }
}