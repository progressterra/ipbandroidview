package com.progressterra.ipbandroidview.ui.main

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.StoreGoods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MainState(
    val items: Flow<PagingData<StoreGoods>> = emptyFlow(),
    val screenState: ScreenState = ScreenState.LOADING
)