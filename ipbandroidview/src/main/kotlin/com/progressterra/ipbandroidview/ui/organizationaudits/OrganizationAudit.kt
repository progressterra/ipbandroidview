package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.runtime.Immutable

@Immutable
data class OrganizationAudit(
    val id: String,
    val name: String,
    val lastTime: String,
)