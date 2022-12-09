package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.AppSettings
import com.progressterra.ipbandroidview.domain.mapper.StoreGoodsMapper
import com.progressterra.ipbandroidview.model.store.StoreGoods

interface GoodsPageUseCase {

    suspend operator fun invoke(
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

        override suspend fun invoke(
            id: String, pageNumber: Int, favorites: List<String>
        ): Result<Pair<Int, List<StoreGoods>>> = withToken { token ->
            val result = eCommerceRepo.getProductsByCategory(
                token,
                id,
                pageNumber,
                AppSettings.PAGE_SIZE,
                0,
                0
            ).getOrThrow()
            result?.numberCurrentPage!! to result.listProducts!!.map {
                mapper.map(it, favorites.contains(it.idUnique!!))
            }
        }
    }
}