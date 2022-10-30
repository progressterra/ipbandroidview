package com.progressterra.ipbandroidview.ui.organizations

sealed class OrganizationsEffect {

    @Suppress("unused")
    class OpenOrganization(val organization: Organization) : OrganizationsEffect()
}