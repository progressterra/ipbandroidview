package com.progressterra.ipbandroidview.dto

import com.progressterra.ipbandroidview.components.CategoryState
import com.progressterra.ipbandroidview.components.SubCategoryState

data class Category(
    override val id: String,
    override val name: String,
    override val image: String,
    val subCategories: List<SubCategory>?
) : CategoryState, SubCategoryState
