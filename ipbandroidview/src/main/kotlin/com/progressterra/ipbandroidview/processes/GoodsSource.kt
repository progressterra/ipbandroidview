package com.progressterra.ipbandroidview.processes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds

class GoodsSource(
    private val fetchGoodsPage: FetchGoodsPage,
    private val categoryId: String,
    private val fetchFavoriteIds: FetchFavoriteIds
) : PagingSource<Int, ProshkaStoreCardState>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProshkaStoreCardState> {
        val nextPage = params.key ?: 1
        val response = fetchFavoriteIds().onSuccess { favorites ->
            fetchGoodsPage(categoryId, nextPage, favorites).onSuccess {
                    return LoadResult.Page(
                        data = it.second,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (it.second.isEmpty()) null else it.first + 1
                    )
                }
        }
        return LoadResult.Error(response.exceptionOrNull()!!)
    }

    override fun getRefreshKey(state: PagingState<Int, ProshkaStoreCardState>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}