package com.progressterra.ipbandroidview.ui.organizations

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class OrganizationViewModel(
    private val
) : ViewModel(), ContainerHost<AuditsState, AuditsEffect>,
    AuditsInteractor {

    override val container: Container<AuditsState, AuditsEffect> = container(
        AuditsState()
    )

    private fun init()
}