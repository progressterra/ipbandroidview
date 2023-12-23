package com.progressterra.ipbandroidview.pages.organizationaudits

import com.progressterra.ipbandroidview.entities.OrganizationAudit

sealed class OrganizationAuditsScreenEvent {

    data object OnMap : OrganizationAuditsScreenEvent()

    data class OnAuditDetails(val audit: OrganizationAudit) : OrganizationAuditsScreenEvent()
}
