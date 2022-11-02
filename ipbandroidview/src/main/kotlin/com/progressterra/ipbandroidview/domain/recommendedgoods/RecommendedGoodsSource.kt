package com.progressterra.ipbandroidview.domain.recommendedgoods

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.dto.GoodsCard

class RecommendedGoodsSource(
    private val goodsPageUseCase: GoodsPageUseCase
) : PagingSource<Int, GoodsCard>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GoodsCard> {
        Log.d("PAGING", "load ${params.key}")
        val nextPage = params.key ?: 1
        val response =
            goodsPageUseCase.goodsPage(DomainConstants.MAIN_DEFAULT_CATEGORY_ID, nextPage)
                .onSuccess {
                    Log.d("PAGING", "success load ${it.first} to ${it.second}")
                    return LoadResult.Page(
                        data = it.second,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = it.first + 1
                    )
                }
        return LoadResult.Error(response.exceptionOrNull()!!)
    }

    override fun getRefreshKey(state: PagingState<Int, GoodsCard>): Int? = state.anchorPosition
}