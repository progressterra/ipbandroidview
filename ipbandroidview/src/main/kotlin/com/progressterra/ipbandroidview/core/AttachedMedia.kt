package com.progressterra.ipbandroidview.core

import androidx.compose.runtime.Immutable

@Immutable
interface AttachedMedia<out T> where T : AttachedMedia<T> {
    val id: String
    val local: Boolean
    val toRemove: Boolean

    fun markToRemove(): T
}