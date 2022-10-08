package com.progressterra.ipbandroidview.ui.organizations

sealed class OrganizationsEffect {

    @Suppress("unused")
    class OnOrganization(val organization: Organization) : OrganizationsEffect()
}