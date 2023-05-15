package com.progressterra.ipbandroidview.pages.favorites

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.storecard.StoreCardMapper
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface FavoriteGoodsUseCase {
    //todo maybe wrong id

    suspend operator fun invoke(): Result<List<StoreCardState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val productRepository: ProductRepository,
        private val mapper: StoreCardMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), FavoriteGoodsUseCase {

        override suspend fun invoke(): Result<List<StoreCardState>> = withToken { token ->
            val favoriteIds = favoriteRepository.getClientEntityByType(
                token, TypeEntities.ONE.ordinal
            ).getOrThrow()!!
            buildList {
                favoriteIds.map { favoriteId ->
                    productRepository.productByGoodsInventoryId(
                        token,
                        favoriteId
                    ).getOrThrow()?.firstOrNull()?.let {
                        add(mapper.map(it))
                    }
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
                    company = "Convallis",
                    price = SimplePrice(1000),
                    imageUrl = "https://placekitten.com/200/300",
                    loan = "Рассрочка: 2 платежа по 150 ₽"
                ), StoreCardState(
                    id = "Kotek 2",
                    name = "Weston",
                    company = "Convallis",
                    price = SimplePrice(2000),
                    imageUrl = "https://placekitten.com/200/300",
                    loan = "Рассрочка: 2 платежа по 150 ₽"
                ), StoreCardState(
                    id = "Kotek 3",
                    name = "Nombre",
                    company = "Convallis",
                    price = SimplePrice(5000),
                    imageUrl = "https://placekitten.com/200/300",
                    loan = "Рассрочка: 2 платежа по 150 ₽"
                )
            )
        )
    }
}