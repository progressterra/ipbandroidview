package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.dto.Checklist

sealed class OrganizationAuditsEffect {

    object Back : OrganizationAuditsEffect()

    @Suppress("unused")
    class OpenChecklist(val checklist: Checklist) : OrganizationAuditsEffect()
}