package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface CatalogUseCase : PagingUseCase<Nothing, CatalogCardState> {

    class Base(
        catalogSource: CatalogSource
    ) : CatalogUseCase, PagingUseCase.Abstract<Nothing, CatalogCardState>(catalogSource)
}