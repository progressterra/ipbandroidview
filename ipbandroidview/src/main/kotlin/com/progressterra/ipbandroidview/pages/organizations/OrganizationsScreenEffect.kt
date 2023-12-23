package com.progressterra.ipbandroidview.pages.organizations

import com.progressterra.ipbandroidview.entities.Organization

sealed class OrganizationsScreenEffect {

    data class OnOrganization(val organization: Organization) : OrganizationsScreenEffect()
}