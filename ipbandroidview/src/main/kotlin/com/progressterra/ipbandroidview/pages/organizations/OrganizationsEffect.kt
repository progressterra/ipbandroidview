package com.progressterra.ipbandroidview.pages.organizations

import com.progressterra.ipbandroidview.entities.Organization

sealed class OrganizationsEffect {

    data class OnOrganization(val organization: Organization) : OrganizationsEffect()
}