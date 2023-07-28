package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidapi.api.product.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort

data class GoodsFilter(
    val categoryId: String? = null,
    val search: String? = null,
    val params: Map<String, List<String>>? = null,
) {

    fun toFilterAndSort() = FilterAndSort(
        listFields = buildList {
            categoryId?.let {
                add(
                    FieldForFilter(
                        fieldName = "nomenclature.listCatalogCategory",
                        listValue = listOf(categoryId),
                        comparison = "equalsStrong"
                    )
                )
            }
            params?.forEach {
                add(
                    FieldForFilter(
                        fieldName = it.key,
                        listValue = it.value,
                        comparison = "containsIgnoreCase"
                    )
                )
            }
        }, searchData = search, sort = null
    )
}