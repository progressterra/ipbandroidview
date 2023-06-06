package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.entities.Id
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun <T : Id> List<T>.replaceById(item: T): List<T> =
    toMutableList().apply { set(indexOfFirst { item.id == it.id }, item) }

fun <T : Id> List<T>.updateById(entity: Id, reduced: (T) -> T): List<T> = toMutableList().apply {
    val index = indexOfFirst { entity.id == it.id }
    set(index, reduced(get(index)))
}

fun Date.print(): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return format.format(this)
}

fun <T> Result<T>.throwOnFailure() {
    this.onFailure { throw it }
}