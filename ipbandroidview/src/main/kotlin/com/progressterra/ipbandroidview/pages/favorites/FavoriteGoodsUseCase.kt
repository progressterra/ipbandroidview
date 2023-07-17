package com.progressterra.ipbandroidview.pages.favorites

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toStoreCardState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FavoriteGoodsUseCase {

    suspend operator fun invoke(): Result<List<StoreCardState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val productRepository: ProductRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), FavoriteGoodsUseCase {

        override suspend fun invoke(): Result<List<StoreCardState>> = withToken { token ->
            val favoriteIds = favoriteRepository.getClientEntityByType(
                token, TypeEntities.ONE.ordinal
            ).getOrThrow()!!
            buildList {
                favoriteIds.map { favoriteId ->
                    val goods = productRepository.productByNomenclatureId(
                        token,
                        favoriteId
                    ).getOrThrow()!!.toGoodsItem().toStoreCardState()
                    add(goods)
                }
            }
        }
    }

    class Test : FavoriteGoodsUseCase {

        override suspend fun invoke(): Result<List<StoreCardState>> = Result.success(
            listOf(
                StoreCardState(
                    id = "Kotek",
                    name = "Weston",
                    price = SimplePrice(1000),
                    imageUrl = "https://placekitten.com/200/300",
                ), StoreCardState(
                    id = "Kotek 2",
                    name = "Weston",
                    price = SimplePrice(2000),
                    imageUrl = "https://placekitten.com/200/300",
                ), StoreCardState(
                    id = "Kotek 3",
                    name = "Nombre",
                    price = SimplePrice(5000),
                    imageUrl = "https://placekitten.com/200/300",
                )
            )
        )
    }
}