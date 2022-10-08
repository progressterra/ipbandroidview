package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.ui.organizations.Organization
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrganizationAuditsViewModel(
    private val organization: Organization,
    private val organizationAuditsUseCase: OrganizationAuditsUseCase
) : ViewModel(), ContainerHost<OrganizationAuditsState, OrganizationAuditsEffect>,
    OrganizationAuditsInteractor {

    override val container: Container<OrganizationAuditsState, OrganizationAuditsEffect> =
        container(
            OrganizationAuditsState(
                organizationName = organization.name,
                organizationAddress = organization.address,
                imageUrl = organization.imageUrl
            )
        )

    init {
        fetch()
    }

    private fun fetch() = intent {
        val audits = organizationAuditsUseCase.organizationsAudits(organization.id)
        reduce { state.copy(audits = audits) }
    }

    override fun onMapClick() {
        TODO("Not yet implemented")
    }
}