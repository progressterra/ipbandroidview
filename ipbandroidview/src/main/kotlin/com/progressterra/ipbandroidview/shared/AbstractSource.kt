package com.progressterra.ipbandroidview.shared

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class AbstractSource<I, O : Any> : PagingSource<Int, O>() {

    var filter: I? = null

    protected abstract suspend fun loadPage(skip: Int, take: Int): Result<List<O>>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, O> {
        val nextPage = params.key ?: 1
        val response = loadPage(
            skip = (nextPage - 1) * Constants.PAGE_SIZE,
            take = Constants.PAGE_SIZE
        ).onSuccess {
            return LoadResult.Page(
                data = it,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (it.isEmpty()) null else nextPage + 1
            )
        }
        return LoadResult.Error(response.exceptionOrNull()!!)
    }

    override fun getRefreshKey(state: PagingState<Int, O>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}