package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class OrganizationAuditsState(
    val id: String = "",
    val organizationName: String = "",
    val organizationAddress: String = "",
    val imageUrl: String = "",
    val audits: List<OrganizationAudit> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)