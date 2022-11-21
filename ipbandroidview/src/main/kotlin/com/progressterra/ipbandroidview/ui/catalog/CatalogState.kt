package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.MainCategory

@Immutable
data class CatalogState(
    val currentCategory: String? = null,
    val categories: List<MainCategory> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)