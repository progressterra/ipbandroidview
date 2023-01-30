package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.product.ProductRepository

interface GoodsByColorUseCase {

    suspend operator fun invoke(goodsId: String, colorName: String): Result<String>

    class Base(
        private val productRepository: ProductRepository
    ) : GoodsByColorUseCase {

        override suspend fun invoke(goodsId: String, colorName: String): Result<String> =
            runCatching {
                productRepository.itemByColor(goodsId, colorName).getOrThrow()!!
            }
    }
}