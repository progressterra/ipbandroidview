package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationInfo

sealed class OrganizationsEffect {

    class Organization(info: OrganizationInfo) : OrganizationsEffect()
}