package com.progressterra.ipbandroidview.pages.organizations

import com.progressterra.ipbandroidview.entities.Organization

sealed class OrganizationsScreenEvent {

    data class OnOrganization(val organization: Organization) : OrganizationsScreenEvent()
}