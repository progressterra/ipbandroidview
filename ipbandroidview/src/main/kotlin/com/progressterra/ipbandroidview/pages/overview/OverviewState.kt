package com.progressterra.ipbandroidview.pages.overview

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.OrganizationOverview
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class OverviewState(
    val overviews: List<OrganizationOverview> = emptyList(),
    val screen: StateColumnState = StateColumnState()
)
