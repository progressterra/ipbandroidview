package com.progressterra.ipbandroidview.shared

import androidx.paging.PagingData
import androidx.paging.map
import com.progressterra.ipbandroidview.entities.AttachedMedia
import com.progressterra.ipbandroidview.entities.Id

/**
 * Ext fun that replaces item in list, item must implement [Id]
 */
fun <T : Id> List<T>.replaceById(item: T): List<T> =
    toMutableList().apply {
        val index = indexOfFirst { item.id == it.id }
        if (index != -1) {
            set(index, item)
        }
    }

/**
 * Ext fun that replaces item in paging data, item must implement [Id]
 */
fun <T : Id> PagingData<T>.replaceById(item: T): PagingData<T> = map {
    if (item.id == it.id) {
        item
    } else {
        it
    }
}

/**
 * Ext fun that marks media to remove
 */
fun <T : AttachedMedia<T>> List<T>.markToRemove(item: T): List<T> = toMutableList().apply { this[indexOfFirst { it.id == item.id }] = item.markToRemove() }

/**
 * Ext fun that creates patch for media (new local non removed media and removed remote media)
 */
fun <T : AttachedMedia<T>> List<T>.formPatch(): List<T> = filter { (it.local && !it.toRemove) || (!it.local && it.toRemove) }


/**
 * Ext fun that updates item in list, item must implement [Id]
 */
fun <T : Id> List<T>.updateById(entity: Id, reduced: (T) -> T): List<T> = toMutableList().apply {
    val index = indexOfFirst { entity.id == it.id }
    if (index != -1) {
        set(index, reduced(get(index)))
    }
}

/**
 * Ext fun that marks last item in list to remove
 */
fun <T : AttachedMedia<T>> List<T>.markLastToRemove(): List<T> = toMutableList().apply {
    val temp = last().markToRemove()
    removeLast()
    add(temp)
}

/**
 * Ext fun that throws exception if result is failure
 */
fun <T> Result<T>.throwOnFailure() {
    onFailure { throw it }
}

/**
 * Ext fun that returns null if exception is thrown
 */
fun <T> tryOrNull(block: () -> T): T? = try {
    block()
} catch (e: Exception) {
    null
}