package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.entities.Partner

sealed class OrganizationsEffect {

    class OpenOrganization(val organization: Organization) : OrganizationsEffect()

    class OpenPartner(val partner: Partner) : OrganizationsEffect()
}