package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.components.CategoryState
import kotlinx.parcelize.Parcelize

interface Category : CategoryState, SubCategory {

    @Parcelize
    data class Base(
        override val id: String,
        override val name: String,
        override val image: String,
        override val subCategories: List<SubCategory>,
        override val hasNext: Boolean
    ) : Category
}
