package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.CatalogMapper
import com.progressterra.ipbandroidview.dto.Category
import com.progressterra.ipbandroidview.dto.NoNestedCategoriesException

interface CatalogUseCase {

    suspend fun catalog(): Result<List<Category>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: IECommerceCoreRepository,
        private val mapper: CatalogMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), CatalogUseCase {

        override suspend fun catalog(): Result<List<Category>> = runCatching {
            val result = withToken { repo.getCatalog(it) }.getOrThrow()?.map { mapper.map(it) }
            if (result.isNullOrEmpty())
                throw NoNestedCategoriesException()
            result
        }
    }
}