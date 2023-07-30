package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface CatalogUseCase : PagingUseCase<Nothing, CatalogCardState> {

    class Base(
        private val manageResources: ManageResources,
        private val obtainAccessToken: ObtainAccessToken,
        private val catalogRepository: CatalogRepository
    ) : CatalogUseCase, PagingUseCase.Abstract<Nothing, CatalogCardState>() {

        override fun createSource() =
            CatalogSource(manageResources, obtainAccessToken, catalogRepository)
    }
}