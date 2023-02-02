package com.progressterra.ipbandroidview.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidview.domain.usecase.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.domain.usecase.store.FetchGoodsPage
import com.progressterra.ipbandroidview.model.store.StoreGoods

class GoodsSource(
    private val fetchGoodsPage: FetchGoodsPage,
    private val categoryId: String,
    private val fetchFavoriteIds: FetchFavoriteIds
) : PagingSource<Int, StoreGoods>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreGoods> {
        val nextPage = params.key ?: 1
        val response = fetchFavoriteIds().onSuccess { favorites ->
            fetchGoodsPage(categoryId, nextPage, favorites)
                .onSuccess {
                    return LoadResult.Page(
                        data = it.second,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (it.second.isEmpty()) null else it.first + 1
                    )
                }
        }
        return LoadResult.Error(response.exceptionOrNull()!!)
    }

    override fun getRefreshKey(state: PagingState<Int, StoreGoods>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}