package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class OrganizationAuditsState(
    val organizationName: String,
    val organizationAddress: String,
    val imageUrl: String,
    val audits: List<OrganizationAudit> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val latitude: Double,
    val longitude: Double
)
