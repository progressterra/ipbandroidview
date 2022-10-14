package com.progressterra.ipbandroidview.ui.organizationaudits

import com.progressterra.ipbandroidview.core.Checklist

sealed class OrganizationAuditsEffect {

    object OnBack : OrganizationAuditsEffect()

    @Suppress("unused")
    class OnChecklist(val checklist: Checklist) : OrganizationAuditsEffect()
}