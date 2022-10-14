package com.progressterra.ipbandroidview.ui.organizationaudits

data class OrganizationAudit(
    val id: String,
    val name: String,
    val lastTime: String,
    val warning: Boolean
)