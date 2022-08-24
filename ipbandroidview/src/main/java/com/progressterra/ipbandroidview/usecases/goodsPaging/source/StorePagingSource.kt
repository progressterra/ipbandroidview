package com.progressterra.ipbandroidview.usecases.goodsPaging.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.IECommersCore
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remotedata.DEFAULT_ID
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import kotlinx.coroutines.delay

internal class StorePagingSource(
    private val pageLoader: PageLoader,
    private val idCategory: String
) : PagingSource<Int, RGGoodsInventoryExt>() {
    override fun getRefreshKey(state: PagingState<Int, RGGoodsInventoryExt>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RGGoodsInventoryExt> {
        return pageLoader.load(params, idCategory)
    }

    companion object {
        const val DEF_PAGE = 1
        const val DEF_PAGE_SIZE = 10
        const val DEF_INITIAL_LOAD = 10
    }
}

internal class PageLoader {
    private val loginApi = LoginApi.newInstance()

    private val ieCoreProduct: IECommersCore.Product = IECommersCore.Product()

    private val attemptsCount = 3
    private val delayTime = 1000L

    private var size = 0

    private var search: String = ""

    suspend fun load(
        params: PagingSource.LoadParams<Int>,
        idCategory: String
    ): PagingSource.LoadResult<Int, RGGoodsInventoryExt> {

        var attempts = 0
        var success = false
        var result: PagingSource.LoadResult<Int, RGGoodsInventoryExt>? = null

        while (!success && attempts < attemptsCount) {
            try {
                result = loading(params, idCategory)
                success = true
            } catch (e: Exception) {
                Log.e(javaClass.simpleName, e.message, e)
                delay(delayTime)
                attempts++
            }
        }

        return result.orIfNull { PagingSource.LoadResult.Error(Exception()) }
    }


    private suspend fun loading(
        params: PagingSource.LoadParams<Int>,
        idCategory: String
    ): PagingSource.LoadResult.Page<Int, RGGoodsInventoryExt> {
        if (search.isBlank() && idCategory == DEFAULT_ID)
            return PagingSource.LoadResult.Page(emptyList(), null, null)

        val page = params.key ?: StorePagingSource.DEF_PAGE
        val token = loginApi.accessToken()


        val response = if (search.isNotBlank())
            ieCoreProduct.searchProductsByCategoryCollapsed(
                accessToken = token,
                searchString = search,
                idCategory = idCategory,
                pageNumberIncome = page,
                pageSizeIncome = params.loadSize,
                sortingField = 0,
                sortingOrder = 0
            ).data.orIfNull { throw NullPointerException("Data is null") }
        else
            ieCoreProduct.getProductsByCategoryCollapsed(
                accessToken = token,
                idCategory = idCategory,
                pageNumberIncome = page,
                pageSizeIncome = params.loadSize,
                sortingField = 0,
                sortingOrder = 0
            ).data.orIfNull { throw NullPointerException("Data is null") }

        val data = response.listProducts
            .orIfNull { throw NullPointerException("listProducts is null") }

        size = response.allQuantityItemsProducts ?: 0

        val totalPages = response.totalPage ?: 0
        val endOfList = totalPages <= page

        return PagingSource.LoadResult.Page(
            data = data,
            prevKey = if (page == StorePagingSource.DEF_PAGE) null else page - 1,
            nextKey = if (endOfList) null else page + 1
        )
    }

    fun updateSearch(search: String) {
        this.search = search
    }
}