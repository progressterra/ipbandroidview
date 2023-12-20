package com.progressterra.ipbandroidview.pages.organizationaudits

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.OrganizationAudit
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

@Immutable
data class OrganizationAuditsState(
    val id: String = "",
    val organizationName: String = "",
    val organizationAddress: String = "",
    val imageUrl: String = "",
    val audits: List<OrganizationAudit> = emptyList(),
    val screen: StateColumnState = StateColumnState(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)