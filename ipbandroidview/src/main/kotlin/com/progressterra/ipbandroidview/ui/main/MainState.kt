package com.progressterra.ipbandroidview.ui.main

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class MainState(
    val items: Flow<PagingData<Goods>> = emptyFlow(),
    val screenState: ScreenState = ScreenState.LOADING
)