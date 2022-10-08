package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.runtime.Immutable

@Immutable
data class OrganizationAuditsState(
    val organizationName: String = "",
    val organizationAddress: String = "",
    val imageUrl: String = "",
    val audits: List<OrganizationAudit> = emptyList()
)
