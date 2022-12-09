package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.composable.CategorySearchBarState
import com.progressterra.ipbandroidview.model.store.Category

data class SubCatalogState(
    val currentCategory: Category = Category(
        id = "",
        name = "",
        subCategories = emptyList(),
        hasNext = false
    ),
    override val keyword: String = "",
    override val expanded: Boolean = false
) : CategorySearchBarState