package com.progressterra.ipbandroidview.ui.organizations

import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class OrganizationsState(
    val organizations: List<Organization> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
