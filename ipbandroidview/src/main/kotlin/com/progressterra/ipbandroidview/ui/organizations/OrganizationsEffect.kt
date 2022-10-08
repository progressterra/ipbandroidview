package com.progressterra.ipbandroidview.ui.organizations

sealed class OrganizationsEffect {

    @Suppress("unused")
    class OnOrganization(val info: Organization) : OrganizationsEffect()
}