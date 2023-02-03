package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.AppSettings
import com.progressterra.ipbandroidview.domain.mapper.PromoCategoryMapper
import com.progressterra.ipbandroidview.domain.mapper.StoreGoodsMapper
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.model.store.StoreGoods

interface PromoGoodsUseCase {

    suspend operator fun invoke(): Result<List<Pair<Category, List<StoreGoods>>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val promoRepository: IPBFavPromoRecRepository,
        private val iECommerceCoreRepository: IECommerceCoreRepository,
        private val mapper: PromoCategoryMapper,
        private val goodsMapper: StoreGoodsMapper,
        private val fetchFavoriteIds: FetchFavoriteIds
    ) : PromoGoodsUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<Pair<Category, List<StoreGoods>>>> =
            withToken { token ->
                val favoriteIds = fetchFavoriteIds().getOrThrow()
                val categories = listOf(
                    promoRepository.getCategoryInfo(AppSettings.RECOMMEND_MAN).getOrThrow()!!,
                    promoRepository.getCategoryInfo(AppSettings.RECOMMEND_WOMAN).getOrThrow()!!,
                    promoRepository.getCategoryInfo(AppSettings.RECOMMEND_KIDS).getOrThrow()!!
                ).map { mapper.map(it) }
                val goods =
                    categories.map { promoRepository.getIDKindOf(it.id).getOrThrow()!! }.map {
                        iECommerceCoreRepository.getProductsByIds(token, it).getOrThrow()!!
                    }.map {
                        it.listProducts!!.map { product ->
                            goodsMapper.map(
                                product,
                                favoriteIds.contains(product.idUnique!!)
                            )
                        }
                    }
                categories.mapIndexed { index, category -> category to goods[index] }
            }
    }
}