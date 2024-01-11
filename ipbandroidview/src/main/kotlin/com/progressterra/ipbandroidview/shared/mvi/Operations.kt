package com.progressterra.ipbandroidview.shared.mvi

import androidx.paging.PagingData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

/**
 * Operations performed by viewmodels
 */
interface Operations {

    fun <T : Any> cachePaging(toBeCached: Flow<PagingData<T>>): Flow<PagingData<T>>

    fun onBackground(block: suspend () -> Unit) : Job
}