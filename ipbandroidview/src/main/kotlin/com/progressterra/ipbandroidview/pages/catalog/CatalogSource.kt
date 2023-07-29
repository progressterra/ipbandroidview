package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidapi.api.catalog.models.FilterAndSort
import com.progressterra.ipbandroidview.entities.toCatalogCardState
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource
import com.progressterra.ipbandroidview.shared.ManageResources

class CatalogSource(
    private val manageResources: ManageResources,
    private val obtainAccessToken: ObtainAccessToken,
    private val repo: CatalogRepository
) : AbstractSource<Nothing, CatalogCardState>() {

    override suspend fun loadPage(skip: Int, take: Int): Result<List<CatalogCardState>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            repo.catalog(
                accessToken = token, filterAndSort = FilterAndSort(
                    listFields = emptyList(), sort = null, searchData = "", skip = skip, take = take
                )
            ).getOrThrow()?.listChildItems?.map { it.toCatalogCardState(manageResources) }
                ?: emptyList()
        }
}