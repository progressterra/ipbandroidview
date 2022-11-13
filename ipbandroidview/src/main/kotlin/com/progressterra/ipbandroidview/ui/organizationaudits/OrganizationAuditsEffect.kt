package com.progressterra.ipbandroidview.ui.organizationaudits

sealed class OrganizationAuditsEffect {

    object Back : OrganizationAuditsEffect()

    @Suppress("unused")
    class OpenChecklist(
        val id: String,
        val placeId: String,
        val isDocument: Boolean,
        val name: String
    ) : OrganizationAuditsEffect()
}