package com.progressterra.ipbandroidview.usecases.goodsPaging

import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.iECommersCoreApi.models.RGGoodsInventoryExt
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.usecases.goodsPaging.source.StoreRepository
import com.progressterra.ipbandroidview.utils.IUseCase
import kotlinx.coroutines.flow.Flow

internal class GetGoodsStoreUseCase : IGetGoodsStoreLibUseCase {
    private val repo: IRepository.Store = StoreRepository()

    override fun invoke(param: String): Flow<PagingData<RGGoodsInventoryExt>> {
        return repo.getStorePage(param)
    }

    override fun updateFilters(search: String) {
        repo.updateSearch(search)
    }
}


interface IGetGoodsStoreLibUseCase : IUseCase.FlowInOut<String, PagingData<RGGoodsInventoryExt>> {
    fun updateFilters(search: String)

    companion object {
        fun IGetGoodsStoreLibUseCase(): IGetGoodsStoreLibUseCase = GetGoodsStoreUseCase()
    }
}