package com.progressterra.ipbandroidview.usecases.catalog.models

import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.CatalogItem
import com.progressterra.ipbandroidview.utils.ui.adapters.catalog.CategoryUILib
import com.progressterra.ipbandroidview.utils.ui.adapters.catalog.SubCategoryUILib

internal class CatalogResponseToUI {
    fun convert(input: List<CatalogItem>?): List<CategoryUILib>? {
        input ?: return null

        return input.map { catalogItem ->
            CategoryUILib(
                id = catalogItem.item?.idUnique ?: "",
                title = catalogItem.item?.name ?: "",
                urlImage = catalogItem.item?.urlImage,
                subCategory = catalogItem.childItems?.map {
                    SubCategoryUILib(
                        id = it.item?.idUnique ?: "",
                        name = it.item?.name ?: "",
                        urlImage = it.item?.urlImage
                    )
                } ?: emptyList()
            )
        }
    }
}