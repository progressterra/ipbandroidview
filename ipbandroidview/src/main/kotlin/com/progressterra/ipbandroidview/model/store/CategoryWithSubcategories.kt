package com.progressterra.ipbandroidview.model.store

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
open class CategoryWithSubcategories(
    override val id: String,
    override val name: String,
    val subCategories: List<CategoryWithSubcategories>,
    val hasNext: Boolean
) : Parcelable, Category

interface Category {

    val id: String

    val name: String
}

class SimpleCategory(
    override val id: String,
    override val name: String
) : Category
