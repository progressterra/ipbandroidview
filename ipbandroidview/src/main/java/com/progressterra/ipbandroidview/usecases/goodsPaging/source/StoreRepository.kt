package com.progressterra.ipbandroidview.usecases.goodsPaging.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.data.IRepository
import kotlinx.coroutines.flow.Flow

internal class StoreRepository(
    sCRMRepository: SCRMRepository
) : IRepository.Store {
    private val pageLoader: PageLoader = PageLoader(sCRMRepository)

    private var storePagingSource: StorePagingSource = StorePagingSource(pageLoader, "")

    override fun getStorePage(
        idCategory: String,
        pageSize: Int,
        initialLoad: Int
    ): Flow<PagingData<RGGoodsInventoryExt>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                initialLoadSize = initialLoad
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