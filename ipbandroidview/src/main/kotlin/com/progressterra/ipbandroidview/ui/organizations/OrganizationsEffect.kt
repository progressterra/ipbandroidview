package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.model.Organization

sealed class OrganizationsEffect {

    class OpenOrganization(val organization: Organization) : OrganizationsEffect()
}