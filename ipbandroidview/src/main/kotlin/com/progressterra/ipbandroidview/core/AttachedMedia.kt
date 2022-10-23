package com.progressterra.ipbandroidview.core

interface AttachedMedia<out T> where T: AttachedMedia<T> {
    val local: Boolean
    val toRemove: Boolean

    fun markToRemove(): T
}