package com.progressterra.ipbandroidview.pages.overview

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class OverviewState(
    val ongoing: Flow<PagingData<ChecklistDocument>> = emptyFlow(),
    val archived: Flow<PagingData<ChecklistDocument>> = emptyFlow(),
    val screen: StateColumnState = StateColumnState()
)
