package com.progressterra.ipbandroidview.pages.organizations

import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class OrganizationsScreenState(
    val organizations: List<Organization> = emptyList(),
    val screen: StateColumnState = StateColumnState()
)