package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.model.checklist.Organization

sealed class OrganizationsEffect {

    class OpenOrganization(val organization: Organization) : OrganizationsEffect()
}