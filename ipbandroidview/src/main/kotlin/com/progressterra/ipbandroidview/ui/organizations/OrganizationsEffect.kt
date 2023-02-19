package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.model.Organization
import com.progressterra.ipbandroidview.model.Partner

sealed class OrganizationsEffect {

    class OpenOrganization(val organization: Organization) : OrganizationsEffect()

    class OpenPartner(val partner: Partner) : OrganizationsEffect()
}