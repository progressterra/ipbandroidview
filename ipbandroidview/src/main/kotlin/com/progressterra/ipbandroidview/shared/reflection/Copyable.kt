package com.progressterra.ipbandroidview.shared.reflection

interface Copyable<out T : Copyable<T>> {

    fun copy(
        key: String,
        values: List<String>
    ): T
}