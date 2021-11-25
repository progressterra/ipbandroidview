package com.progressterra.ipbandroidview.utils.extensions

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun CombinedLoadStates.isInitialLoad(): Boolean {
    val loading = source.append as? LoadState.Loading
        ?: source.prepend as? LoadState.Loading
        ?: source.refresh as? LoadState.Loading
        ?: append as? LoadState.Loading
        ?: prepend as? LoadState.Loading
        ?: refresh as? LoadState.Loading
    return loading != null
}

fun CombinedLoadStates.isLoading(): Boolean {
    val loading = source.append as? LoadState.Loading
        ?: source.prepend as? LoadState.Loading
        ?: append as? LoadState.Loading
        ?: prepend as? LoadState.Loading
    return loading != null
}

fun CombinedLoadStates.isError(): Boolean {
    val error = source.append as? LoadState.Error
        ?: source.prepend as? LoadState.Error
        ?: source.refresh as? LoadState.Error
        ?: append as? LoadState.Error
        ?: prepend as? LoadState.Error
        ?: refresh as? LoadState.Error
    return error != null
}