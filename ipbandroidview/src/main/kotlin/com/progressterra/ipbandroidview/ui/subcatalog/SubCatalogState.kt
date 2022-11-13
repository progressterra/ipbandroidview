package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.SubCategory

@Immutable
data class SubCatalogState(
    val currentCategory: SubCategory? = null
)