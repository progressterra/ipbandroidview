package com.progressterra.ipbandroidview.shared.mvi

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * @property pageSize - size of page to load
 * @property filter - filter to apply to the source
 * @property loadPage - function to load page, where first in result pair is count of raw source items and second is list of items
 */
abstract class AbstractSource<I, O : Any> : PagingSource<Int, O>() {

    var filter: I? = null

    abstract val pageSize: Int

    protected abstract suspend fun loadPage(skip: Int, take: Int): Result<Pair<Int, List<O>>>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, O> {
        val nextPage = params.key ?: 1
        val response = loadPage(
            skip = (nextPage - 1) * pageSize,
            take = pageSize
        ).onSuccess {
            return LoadResult.Page(
                data = it.second,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (it.first < pageSize) null else nextPage + 1
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