package com.progressterra.ipbandroidview.pages.organizations

import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.entities.Partner

sealed class OrganizationsEffect {

    data class OnOrganization(val organization: Organization) : OrganizationsEffect()

    data class OnPartner(val partner: Partner) : OrganizationsEffect()

}