package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.dto.SubCategory

@Immutable
data class SubCatalogState(
    val currentCategory: SubCategory? = null,
)