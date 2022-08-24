package com.progressterra.ipbandroidview.usecases.goodsPaging

import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.usecases.goodsPaging.source.StorePagingSource
import com.progressterra.ipbandroidview.usecases.goodsPaging.source.StoreRepository
import com.progressterra.ipbandroidview.utils.IUseCase
import kotlinx.coroutines.flow.Flow

internal class GetGoodsStoreUseCase : IGetGoodsStoreLibUseCase {
    private val repo: IRepository.Store = StoreRepository()

    override fun invoke(param: PaginationParam): Flow<PagingData<RGGoodsInventoryExt>> {
        return repo.getStorePage(
            idCategory = param.idCategory,
            pageSize = param.pageSize,
            initialLoad = param.initialLoad
        )
    }

    override fun updateFilters(search: String) {
        repo.updateSearch(search)
    }
}


interface IGetGoodsStoreLibUseCase :
    IUseCase.FlowInOut<PaginationParam, PagingData<RGGoodsInventoryExt>> {
    fun updateFilters(search: String)

    companion object {
        fun IGetGoodsStoreLibUseCase(): IGetGoodsStoreLibUseCase = GetGoodsStoreUseCase()
    }
}

data class PaginationParam(
    val idCategory: String,
    val pageSize: Int = StorePagingSource.DEF_PAGE_SIZE,
    val initialLoad: Int = StorePagingSource.DEF_INITIAL_LOAD
)