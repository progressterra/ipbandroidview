package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidapi.api.iecommerce.model.CatalogItem
import com.progressterra.ipbandroidapi.ext.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.entities.CategoryWithSubcategories

interface SubCatalogMapper : Mapper<CatalogItem, CategoryWithSubcategories> {

    class Base(
        manageResources: ManageResources
    ) : SubCatalogMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: CatalogItem): CategoryWithSubcategories {
            val hasNext = !data.childItems.isNullOrEmpty()
            return CategoryWithSubcategories(
                id = data.item?.idUnique!!,
                name = data.item?.name ?: noData,
                subCategories = data.childItems?.map { map(it) }.orIfNull { emptyList() },
                hasNext = hasNext
            )
        }
    }
}