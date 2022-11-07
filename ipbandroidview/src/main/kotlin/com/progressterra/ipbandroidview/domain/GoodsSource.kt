package com.progressterra.ipbandroidview.domain

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidview.dto.Goods

class GoodsSource(
    private val goodsPageUseCase: GoodsPageUseCase
) : PagingSource<Int, Goods>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Goods> {
        Log.d("PAGING", "load ${params.key}")
        val nextPage = params.key ?: 1
        val response =
            goodsPageUseCase.goodsPage(DomainConstants.MAIN_DEFAULT_CATEGORY_ID, nextPage)
                .onSuccess {
                    Log.d("PAGING", "success load ${it.first} to ${it.second}")
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