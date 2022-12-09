package com.progressterra.ipbandroidview.model.store

import androidx.compose.runtime.Immutable

@Immutable
class MainCategory(
    val image: String,
    id: String,
    name: String,
    subCategories: List<Category>,
    hasNext: Boolean
) : Category(id = id, name = name, subCategories = subCategories, hasNext = hasNext)

