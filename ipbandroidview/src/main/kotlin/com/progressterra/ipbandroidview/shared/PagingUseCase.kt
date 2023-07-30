package com.progressterra.ipbandroidview.shared

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.shared.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

interface PagingUseCase<I, O : Any> {

    suspend operator fun invoke(filter: I): Result<Flow<PagingData<O>>>

    suspend operator fun invoke(): Result<Flow<PagingData<O>>>

    abstract class Abstract<I, O : Any> : PagingUseCase<I, O> {

        abstract fun createSource(): AbstractSource<I, O>

        override suspend operator fun invoke(filter: I): Result<Flow<PagingData<O>>> = runCatching {
            val source = createSource()
            source.filter = filter
            Pager(PagingConfig(PAGE_SIZE)) {
                source
            }.flow
        }

        override suspend fun invoke(): Result<Flow<PagingData<O>>> = runCatching {
            Pager(PagingConfig(PAGE_SIZE)) {
                createSource()
            }.flow
        }
    }
}