package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class WantThisRequestsScreenState(
    val screen: StateColumnState = StateColumnState(),
    val items: Flow<PagingData<WantThisCardState>> = emptyFlow()
)