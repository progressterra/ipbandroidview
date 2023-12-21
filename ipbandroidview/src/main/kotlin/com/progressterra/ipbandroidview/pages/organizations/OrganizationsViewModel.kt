package com.progressterra.ipbandroidview.pages.organizations

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.processes.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OrganizationsViewModel(
    private val allOrganizationsUseCase: AllOrganizationsUseCase,
) : AbstractNonInputViewModel<OrganizationsState, OrganizationsEffect>(), UseOrganizationsScreen {

    override fun createInitialState() = OrganizationsState()

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var isSuccess = true
            allOrganizationsUseCase().onSuccess { list ->
                emitState { it.copy(organizations = list) }
            }.onFailure {
                isSuccess = false
            }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: OrganizationsScreenEvent) {
        when (event) {
            is OrganizationsScreenEvent.OnOrganization -> postEffect(
                OrganizationsEffect.OnOrganization(
                    event.organization
                )
            )

            is OrganizationsScreenEvent.OnPartner -> postEffect(
                OrganizationsEffect.OnPartner(
                    currentState.partner
                )
            )
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: TopBarEvent) = Unit
}