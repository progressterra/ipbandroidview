package com.progressterra.ipbandroidview.core

interface AttachedMedia<out T> where T : AttachedMedia<T> {
    val id: String
    val local: Boolean
    val toRemove: Boolean

    fun markToRemove(): T
}