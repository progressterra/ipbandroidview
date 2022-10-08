package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.OrganizationAuditsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrganizationAuditsViewModel(
    private val info: OrganizationInfo,
    private val organizationAuditsUseCase: OrganizationAuditsUseCase
) : ViewModel(), ContainerHost<OrganizationAuditsState, OrganizationAuditsEffect>,
    OrganizationAuditsInteractor {

    override val container: Container<OrganizationAuditsState, OrganizationAuditsEffect> =
        container(
            OrganizationAuditsState(
                organizationName = info.name,
                organizationAddress = info.address,
                imageUrl = info.imageUrl
            )
        )

    init {
        fetch()
    }

    private fun fetch() = intent {
        val audits = organizationAuditsUseCase.organizationsAudits(info.id)
        reduce { state.copy(audits = audits) }
    }

    override fun onMapClick() {
        TODO("Not yet implemented")
    }
}