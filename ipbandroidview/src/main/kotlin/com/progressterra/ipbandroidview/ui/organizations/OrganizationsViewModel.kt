package com.progressterra.ipbandroidview.ui.organizations

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class OrganizationsViewModel(
) : ViewModel(), ContainerHost<OrganizationsState, OrganizationsEffect>,
    OrganizationsInteractor {

    override val container: Container<OrganizationsState, OrganizationsEffect> = container(
        OrganizationsState()
    )

}