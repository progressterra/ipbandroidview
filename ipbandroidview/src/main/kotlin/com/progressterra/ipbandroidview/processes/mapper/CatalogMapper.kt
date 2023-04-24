package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidapi.api.iecommerce.model.CatalogItem
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.Mapper

interface CatalogMapper : Mapper<CatalogItem, CatalogCardState> {

    class Base(manageResources: ManageResources) :
        CatalogMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: CatalogItem): CatalogCardState {
            return CatalogCardState(id = data.item?.idUnique!!,
                name = data.item?.name ?: noData,
                imageUrl = data.item?.urlImage ?: "",
                children = data.childItems?.map { map(it) } ?: emptyList()
            )
        }
    }
}