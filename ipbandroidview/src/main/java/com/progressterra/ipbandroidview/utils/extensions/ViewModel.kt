package com.progressterra.ipbandroidview.utils.extensions

fun <T> lazyUnsafe(initializer: () -> T) =
    lazy(mode = LazyThreadSafetyMode.NONE) { initializer.invoke() }