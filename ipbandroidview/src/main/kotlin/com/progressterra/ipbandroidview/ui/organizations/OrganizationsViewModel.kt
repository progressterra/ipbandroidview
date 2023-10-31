package com.progressterra.ipbandroidview.ui.organizations

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.PartnerBlockEvent
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.usecase.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.model.Organization
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrganizationsViewModel(
    private val allOrganizationsUseCase: AllOrganizationsUseCase,
    private val fetchPartnerUseCase: FetchPartnerUseCase
) : ViewModel(), ContainerHost<OrganizationsState, OrganizationsEffect>, OrganizationsInteractor {

    override val container: Container<OrganizationsState, OrganizationsEffect> = container(
        OrganizationsState()
    )

    init {
        refresh()
    }

    override fun handleEvent(event: PartnerBlockEvent) {
        intent {
            postSideEffect(OrganizationsEffect.OpenPartner(state.partner))
        }
    }

    override fun refresh() {
        intent {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            allOrganizationsUseCase().onSuccess {
                reduce { state.copy(organizations = it) }
                fetchPartnerUseCase().onSuccess { partner ->
                    reduce { state.copy(partner = partner, screenState = ScreenState.SUCCESS) }
                }.onFailure {
                    reduce { state.copy(screenState = ScreenState.ERROR) }
                }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun onOrganizationDetails(organization: Organization) {
        intent {
            postSideEffect(OrganizationsEffect.OpenOrganization(organization))
        }
    }
}