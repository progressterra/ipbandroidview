package com.progressterra.ipbandroidview.model.store

import androidx.compose.runtime.Immutable

@Immutable
class MainCategory(
    val image: String,
    id: String,
    name: String,
    subCategories: List<CategoryWithSubcategories>,
    hasNext: Boolean
) : CategoryWithSubcategories(id = id, name = name, subCategories = subCategories, hasNext = hasNext)

