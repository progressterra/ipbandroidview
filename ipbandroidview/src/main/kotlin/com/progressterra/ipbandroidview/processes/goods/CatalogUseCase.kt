package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidapi.api.catalog.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.toCatalogCardState
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.mvi.CacheUseCase

interface CatalogUseCase : CacheUseCase<CatalogCardState> {

    suspend operator fun invoke()

    class Base(
        private val catalogRepository: CatalogRepository,
        private val manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken, makeToastUseCase: MakeToastUseCase
    ) : CatalogUseCase, AbstractCacheTokenUseCase<CatalogCardState>(
        obtainAccessToken,
        makeToastUseCase, manageResources
    ) {
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