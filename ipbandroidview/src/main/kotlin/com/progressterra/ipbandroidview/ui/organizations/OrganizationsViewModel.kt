package com.progressterra.ipbandroidview.ui.organizations

import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.AllOrganizationsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrganizationsViewModel(
    private val allOrganizationsUseCase: AllOrganizationsUseCase
) : ViewModel(), ContainerHost<OrganizationsState, OrganizationsEffect>,
    OrganizationsInteractor {

    override val container: Container<OrganizationsState, OrganizationsEffect> = container(
        OrganizationsState()
    )

    init {
        initOrganizations()
    }

    private fun initOrganizations() = intent {
        Log.d("ORG", "initOrganizations: ")
        val result = allOrganizationsUseCase.allOrganizations()
        reduce { state.copy(organizations = result) }
    }

    override fun onOrganization(id: String) {

    }
}