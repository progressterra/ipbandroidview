package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.GoodsColorMapper
import com.progressterra.ipbandroidview.model.store.GoodsColor

interface GoodsColorsUseCase {

    suspend operator fun invoke(id: String): Result<List<GoodsColor>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val productRepository: ProductRepository,
        private val colorMapper: GoodsColorMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsColorsUseCase {

        override suspend fun invoke(id: String): Result<List<GoodsColor>> = withToken {
            productRepository.colorsForItem(id).getOrThrow()!!
                .map { color -> colorMapper.map(color) }
        }
    }
}