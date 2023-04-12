package com.progressterra.ipbandroidview.processes.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.widgets.catalogitems.CatalogItemsState

interface CatalogUseCase {

    suspend operator fun invoke(): Result<CatalogItemsState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: IECommerceCoreRepository,
        private val mapper: CatalogMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), CatalogUseCase {

        override suspend fun invoke(): Result<CatalogItemsState> = withToken { token ->
            CatalogItemsState(repo.getCatalog(token).getOrThrow()?.first()?.childItems?.map {
                mapper.map(
                    it
                )
            } ?: emptyList())
        }
    }
}