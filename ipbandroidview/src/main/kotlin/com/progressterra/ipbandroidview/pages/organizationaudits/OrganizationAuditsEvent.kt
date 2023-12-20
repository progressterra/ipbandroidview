package com.progressterra.ipbandroidview.pages.organizationaudits

import com.progressterra.ipbandroidview.entities.OrganizationAudit

sealed class OrganizationAuditsEvent {

    data object OnMap : OrganizationAuditsEvent()

    data class OnAuditDetails(val audit: OrganizationAudit) : OrganizationAuditsEvent()
}
