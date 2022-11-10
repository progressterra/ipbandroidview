package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.GoodsMapper
import com.progressterra.ipbandroidview.dto.Goods

interface GoodsPageUseCase {

    suspend fun goodsPage(id: String, pageNumber: Int): Result<Pair<Int, List<Goods>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val mapper: GoodsMapper,
        private val eCommerceRepo: IECommerceCoreRepository,
        private val favoriteRepository: IPBFavPromoRecRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsPageUseCase {

        //todo remove favorite checking from there

        override suspend fun goodsPage(
            id: String, pageNumber: Int
        ): Result<Pair<Int, List<Goods>>> = runCatching {
            val favorites = withToken {
                favoriteRepository.getClientEntityByType(
                    it, TypeOfEntity.PRODUCT
                )
            }.getOrThrow()
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