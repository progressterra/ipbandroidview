package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationInfo

sealed class OrganizationsEffect {

    @Suppress("unused")
    class Organization(val info: OrganizationInfo) : OrganizationsEffect()
}