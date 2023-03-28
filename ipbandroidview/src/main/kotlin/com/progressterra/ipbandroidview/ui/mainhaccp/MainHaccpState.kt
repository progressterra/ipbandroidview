package com.progressterra.ipbandroidview.ui.mainhaccp

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.OrganizationOverview
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.entities.Partner

@Immutable
data class MainHaccpState(
    val partner: Partner = Partner(),
    val overviews: List<OrganizationOverview> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
