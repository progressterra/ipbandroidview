package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface CatalogUseCase {

    suspend operator fun invoke(): Result<CatalogCardState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: IECommerceCoreRepository,
        private val mapper: CatalogMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), CatalogUseCase {

        override suspend fun invoke(): Result<CatalogCardState> = withToken { token ->
            CatalogCardState(subCategories = repo.getCatalog(token).getOrThrow()
                ?.first()?.childItems?.map {
                    mapper.map(
                        it
                    )
                } ?: emptyList())
        }
    }
}