package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationInfo

interface OrganizationsInteractor {

    fun onOrganization(info: OrganizationInfo)

    class Empty : OrganizationsInteractor {

        override fun onOrganization(info: OrganizationInfo) = Unit
    }
}