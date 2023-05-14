package com.progressterra.ipbandroidview.processes.goods

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidview.features.storecard.StoreCardState

class GoodsSource(
    private val fetchGoodsPage: FetchGoodsPage,
    private val filterAndSort: FilterAndSort
) : PagingSource<Int, StoreCardState>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreCardState> {
        val nextPage = params.key ?: 1
        val response = fetchGoodsPage(
            filterAndSort.copy(
                skip = nextPage * PAGE_SIZE,
                take = PAGE_SIZE
            )
        ).onSuccess {
            return LoadResult.Page(
                data = it,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (it.isEmpty()) null else nextPage + 1
            )
        }
        return LoadResult.Error(response.exceptionOrNull()!!)
    }

    override fun getRefreshKey(state: PagingState<Int, StoreCardState>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {

        private const val PAGE_SIZE = 10
    }
}