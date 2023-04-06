package com.progressterra.ipbandroidview.processes.usecase.store

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation

interface SizeTableUseCase {

    suspend operator fun invoke(id: String): Result<String>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val productRepository: ProductRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), SizeTableUseCase {

        override suspend fun invoke(id: String): Result<String> = withToken {
            productRepository.sizeTableForItem(id).getOrThrow() ?: ""
        }
    }
}