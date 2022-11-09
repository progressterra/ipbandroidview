package com.progressterra.ipbandroidview.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidview.dto.Goods

class GoodsSource(
    private val goodsPageUseCase: GoodsPageUseCase,
    private val categoryId: String
) : PagingSource<Int, Goods>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Goods> {
        val nextPage = params.key ?: 1
        val response =
            goodsPageUseCase.goodsPage(categoryId, nextPage)
                .onSuccess {
                    return LoadResult.Page(
                        data = it.second,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = if (it.second.isEmpty()) null else it.first + 1
                    )
                }
        return LoadResult.Error(response.exceptionOrNull()!!)
    }

    override fun getRefreshKey(state: PagingState<Int, Goods>): Int? = state.anchorPosition
}