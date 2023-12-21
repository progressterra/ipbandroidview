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

fun <T : AttachedMedia<T>> List<T>.markToRemove(item: T): List<T> = toMutableList().apply { this[indexOfFirst { it.id == item.id }] = item.markToRemove() }

fun <T : AttachedMedia<T>> List<T>.formPatch(): List<T> = filter { (it.local && !it.toRemove) || (!it.local && it.toRemove) }

fun <T : Id> List<T>.updateById(entity: Id, reduced: (T) -> T): List<T> = toMutableList().apply {
    val index = indexOfFirst { entity.id == it.id }
    if (index != -1) {
        set(index, reduced(get(index)))
    }
}

fun <T : AttachedMedia<T>> List<T>.markLastToRemove(): List<T> = toMutableList().apply {
    val temp = last().markToRemove()
    removeLast()
    add(temp)
}

fun <T> Result<T>.throwOnFailure() {
    onFailure { throw it }
}

fun <T> tryOrNull(block: () -> T): T? = try {
    block()
} catch (e: Exception) {
    null
}