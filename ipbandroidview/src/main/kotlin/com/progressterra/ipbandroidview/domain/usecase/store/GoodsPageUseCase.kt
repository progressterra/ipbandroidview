package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.mapper.StoreGoodsMapper
import com.progressterra.ipbandroidview.model.StoreGoods

interface GoodsPageUseCase {

    suspend fun goodsPage(
        id: String,
        pageNumber: Int,
        favorites: List<String>
    ): Result<Pair<Int, List<StoreGoods>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val mapper: StoreGoodsMapper,
        private val eCommerceRepo: IECommerceCoreRepository,
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsPageUseCase {

        override suspend fun goodsPage(
            id: String, pageNumber: Int, favorites: List<String>
        ): Result<Pair<Int, List<StoreGoods>>> = runCatching {
            val result = withToken {
                eCommerceRepo.getProductsByCategory(
                    it,
                    id,
                    pageNumber,
                    DomainConstants.PAGE_SIZE,
                    0,
                    0
                )
            }.getOrThrow()
            result?.numberCurrentPage!! to result.listProducts!!.map {
                mapper.map(it, favorites.contains(it.idUnique!!))
            }
        }
    }
}