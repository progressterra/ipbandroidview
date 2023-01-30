package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface SizeTableForItemUseCase {

    suspend operator fun invoke(goodsId: String): Result<String>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val productRepository: ProductRepository
    ) : SizeTableForItemUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(goodsId: String): Result<String> = runCatching {
            productRepository.sizeTableForItem(goodsId).getOrThrow()!!
        }
    }
}