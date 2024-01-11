package com.progressterra.ipbandroidview.entities


interface AttachedMedia<out T> : Id where T : AttachedMedia<T> {
    val local: Boolean
    val toRemove: Boolean

    fun markToRemove(): T
}