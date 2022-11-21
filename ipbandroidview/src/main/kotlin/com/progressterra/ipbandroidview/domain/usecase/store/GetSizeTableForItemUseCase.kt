package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation

interface SizeTableForItemUseCase {

    suspend fun tableUrl(): Result<String>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val productRepository: ProductRepository
    ) : SizeTableForItemUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun tableUrl(): Result<String> {
            TODO("Not yet implemented")
        }
    }
}