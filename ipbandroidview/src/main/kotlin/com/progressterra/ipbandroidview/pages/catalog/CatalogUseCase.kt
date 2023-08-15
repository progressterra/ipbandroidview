package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidapi.api.catalog.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.toCatalogCardState
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface CatalogUseCase {

    suspend operator fun invoke(): Result<CatalogCardState>

    class Base(
        private val catalogRepository: CatalogRepository,
        private val manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken
    ) : CatalogUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<CatalogCardState> = withToken {
            catalogRepository.catalog(
                accessToken = it, filterAndSort = FilterAndSort(
                    listFields = emptyList(), sort = null, searchData = "", skip = 0, take = 1
                )
            ).getOrThrow()?.toCatalogCardState { resId ->
                manageResources.string(resId)
            } ?: CatalogCardState()
        }
    }
}