package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
open class Category(
    val id: String,
    val name: String,
    val subCategories: List<Category>,
    val hasNext: Boolean
) : Parcelable
