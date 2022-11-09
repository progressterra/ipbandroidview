package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.iecommerce.model.CatalogItem
import com.progressterra.ipbandroidapi.ext.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.dto.Category

interface CatalogMapper : Mapper<CatalogItem, Category> {

    class Base(manageResources: ManageResources, private val subCatalogMapper: SubCatalogMapper) :
        CatalogMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: CatalogItem): Category {
            val hasNext = !data.childItems.isNullOrEmpty()
            return Category.Base(id = data.item?.idUnique!!,
                name = data.item?.name ?: noData,
                image = data.item?.urlImage ?: "",
                subCategories = data.childItems?.map { subCatalogMapper.map(it) }
                    .orIfNull { emptyList() },
                hasNext = hasNext
            )
        }
    }
}