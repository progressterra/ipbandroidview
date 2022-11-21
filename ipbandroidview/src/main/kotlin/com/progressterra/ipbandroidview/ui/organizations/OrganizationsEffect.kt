package com.progressterra.ipbandroidview.ui.organizations

sealed class OrganizationsEffect {

    class OpenOrganization(val organization: Organization) : OrganizationsEffect()
}