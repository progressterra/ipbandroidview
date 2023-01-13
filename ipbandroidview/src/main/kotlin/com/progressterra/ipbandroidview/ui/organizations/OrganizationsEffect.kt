package com.progressterra.ipbandroidview.ui.organizations

import com.progressterra.ipbandroidview.model.checklist.Organization
import com.progressterra.ipbandroidview.model.partner.Partner

sealed class OrganizationsEffect {

    class OpenOrganization(val organization: Organization) : OrganizationsEffect()

    class OpenPartner(val partner: Partner): OrganizationsEffect()
}