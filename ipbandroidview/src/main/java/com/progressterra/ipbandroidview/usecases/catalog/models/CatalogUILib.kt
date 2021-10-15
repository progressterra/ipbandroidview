package com.progressterra.ipbandroidview.usecases.catalog.models

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.ui.adapters.catalog.CategoryUILib
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogUILib(
    val title: String,
    val categories: List<CategoryUILib>
) : Parcelable