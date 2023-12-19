package com.progressterra.ipbandroidview.pages.organizations

import androidx.paging.PagingData
import com.google.errorprone.annotations.Immutable
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.entities.Partner
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class OrganizationsState(
    val organizations: List<Organization> = emptyList(),
    val partner: Partner = Partner(),
    val screen: StateColumnState = StateColumnState()
)