package com.progressterra.ipbandroidview.usecases.goodsPaging.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.iECommersCoreApi.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.data.IRepository
import kotlinx.coroutines.flow.Flow

internal class StoreRepository : IRepository.Store {
    private val pageLoader: PageLoader = PageLoader()

    private var storePagingSource: StorePagingSource = StorePagingSource(pageLoader, "")


    override fun getStorePage(idCategory: String): Flow<PagingData<RGGoodsInventoryExt>> {
        return Pager(
            config = PagingConfig(
                pageSize = StorePagingSource.DEF_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = StorePagingSource.DEF_INITIAL_LOAD
            ),
            pagingSourceFactory = {
                storePagingSource = StorePagingSource(pageLoader, idCategory)
                storePagingSource
            }
        ).flow
    }

    override fun updateSearch(search: String) {
        pageLoader.updateSearch(search)
        storePagingSource.invalidate()
    }
}