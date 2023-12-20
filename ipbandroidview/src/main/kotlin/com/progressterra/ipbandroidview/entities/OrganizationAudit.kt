package com.progressterra.ipbandroidview.entities

data class OrganizationAudit(
    override val id: String,
    val name: String,
    val lastTime: String,
) : Id