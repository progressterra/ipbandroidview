package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.composable.component.CategorySearchBarState
import com.progressterra.ipbandroidview.model.CategoryWithSubcategories

data class SubCatalogState(
    val currentCategoryWithSubcategories: CategoryWithSubcategories = CategoryWithSubcategories(
        id = "",
        name = "",
        subCategories = emptyList(),
        hasNext = false
    ),
    override val keyword: String = "",
    override val expanded: Boolean = false
) : CategorySearchBarState