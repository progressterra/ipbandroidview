package com.progressterra.ipbandroidview.pages.favorites

import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface FavoriteGoodsUseCase {

    suspend operator fun invoke(): Result<Flow<PagingData<StoreCardState>>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val productRepository: ProductRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), FavoriteGoodsUseCase {

        override suspend fun invoke(): Result<Flow<PagingData<StoreCardState>>> =
            withToken { token ->
                val favoriteIds = favoriteRepository.getClientEntityByType(
                    token, TypeEntities.ONE.ordinal
                ).getOrThrow()!!
                flowOf(
                    PagingData.from(
                        buildList {
                            favoriteIds.map { favoriteId ->
                                val goods = productRepository.productByNomenclatureId(
                                    token,
                                    favoriteId
                                ).getOrThrow()!!.toGoodsItem().toStoreCardState()
                                add(goods)
                            }
                        }
                    )
                )
            }
    }
}