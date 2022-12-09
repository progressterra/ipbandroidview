package com.progressterra.ipbandroidview.model.store

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
open class Category(
    val id: String,
    val name: String,
    val subCategories: List<Category>,
    val hasNext: Boolean
) : Parcelable
