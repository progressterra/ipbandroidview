package com.progressterra.ipbandroidview.shared.reflection

import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Copyable<T>> Map<String, List<String>>.extractFromMap(
    defaultValue: T
): T {
    var returned = defaultValue
    T::class.primaryConstructor?.parameters?.mapNotNull { it.name }?.forEach {
        returned = returned.copy(it, this[it] ?: emptyList())
    }
    return returned
}