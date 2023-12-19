package com.progressterra.ipbandroidview.pages.organizations

import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.entities.Partner
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class OrganizationsState(
    val organizations: List<Organization> = emptyList(),
    val partner: Partner = Partner(),
    val screen: StateColumnState = StateColumnState()
)