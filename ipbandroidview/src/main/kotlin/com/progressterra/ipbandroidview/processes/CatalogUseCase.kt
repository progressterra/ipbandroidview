package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidapi.api.catalog.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.toCatalogCardState
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface CatalogUseCase : CacheUseCase<CatalogCardState> {

    suspend operator fun invoke()

    class Base(
        private val catalogRepository: CatalogRepository,
        private val manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken
    ) : CatalogUseCase, AbstractCacheTokenUseCase<CatalogCardState>(obtainAccessToken) {
        override suspend fun invoke() {
            withCache {
                catalogRepository.catalog(
                    accessToken = it, filterAndSort = FilterAndSort(
                        listFields = emptyList(),
                        sort = null,
                        searchData = "",
                        skip = 0,
                        take = 1
                    )
                ).getOrThrow()?.toCatalogCardState { resId ->
                    manageResources.string(resId)
                } ?: CatalogCardState()
            }
        }
    }
}