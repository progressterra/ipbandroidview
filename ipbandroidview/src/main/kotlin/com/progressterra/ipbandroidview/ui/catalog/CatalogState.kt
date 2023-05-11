package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.CatalogBarComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.MainCategory

@Immutable
data class CatalogState(
    val categories: List<MainCategory> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val catalogBarState: CatalogBarComponentState = CatalogBarComponentState()
)