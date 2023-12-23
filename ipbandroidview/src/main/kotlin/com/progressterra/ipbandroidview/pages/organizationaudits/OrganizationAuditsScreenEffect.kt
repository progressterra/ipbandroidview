package com.progressterra.ipbandroidview.pages.organizationaudits

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus

sealed class OrganizationAuditsScreenEffect {

    data object OnBack : OrganizationAuditsScreenEffect()

    data class OnChecklist(val data: Pair<AuditDocument, ChecklistStatus>) :
        OrganizationAuditsScreenEffect()
}