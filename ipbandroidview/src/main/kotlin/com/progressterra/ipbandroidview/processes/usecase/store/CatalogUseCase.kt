package com.progressterra.ipbandroidview.processes.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.orIfNull
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.entities.MainCategory

interface CatalogUseCase {

    suspend operator fun invoke(): Result<List<MainCategory>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: IECommerceCoreRepository,
        private val mapper: CatalogMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), CatalogUseCase {

        override suspend fun invoke(): Result<List<MainCategory>> = withToken { token ->
            repo.getCatalog(token).getOrThrow()?.first()?.childItems?.map {
                mapper.map(
                    it
                )
            }.orIfNull { emptyList() }
        }
    }
}