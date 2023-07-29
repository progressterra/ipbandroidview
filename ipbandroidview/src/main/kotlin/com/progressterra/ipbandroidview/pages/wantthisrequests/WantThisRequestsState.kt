package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.ScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class WantThisRequestsState(
    val screen: ScreenState = ScreenState.LOADING,
    val items: Flow<PagingData<WantThisCardState>> = emptyFlow()
)