package com.progressterra.ipbandroidview.ui.goods

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.components.StateBoxState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Goods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GoodsState(
    val currentCategory: String? = null,
    val items: Flow<PagingData<Goods>> = emptyFlow(),
    override val screenState: ScreenState = ScreenState.LOADING
) : StateBoxState
