package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Category

@Immutable
data class CatalogState(
    val currentCategory: String? = null,
    val categories: List<Category> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)