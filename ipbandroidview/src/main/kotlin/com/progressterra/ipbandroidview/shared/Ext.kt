package com.progressterra.ipbandroidview.shared

import androidx.paging.PagingData
import androidx.paging.map
import com.progressterra.ipbandroidview.entities.Id

fun <T : Id> List<T>.replaceById(item: T): List<T> =
    toMutableList().apply {
        val index = indexOfFirst { item.id == it.id }
        if (index != -1) {
            set(index, item)
        }
    }

fun <T : Id> PagingData<T>.replaceById(item: T): PagingData<T> = map {
    if (item.id == it.id) {
        item
    } else {
        it
    }
}

fun <T : Id> List<T>.updateById(entity: Id, reduced: (T) -> T): List<T> = toMutableList().apply {
    val index = indexOfFirst { entity.id == it.id }
    if (index != -1) {
        set(index, reduced(get(index)))
    }
}

fun <T> Result<T>.throwOnFailure() {
    this.onFailure { throw it }
}