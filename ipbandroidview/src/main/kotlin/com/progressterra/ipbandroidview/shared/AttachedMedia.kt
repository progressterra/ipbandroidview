package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidview.entities.Id


interface AttachedMedia<out T> : Id where T : AttachedMedia<T> {
    val local: Boolean
    val toRemove: Boolean

    fun markToRemove(): T
}