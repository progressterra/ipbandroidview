package com.progressterra.ipbandroidview.pages.organizationaudits

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenMapUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OrganizationAuditsScreenViewModel(
    private val organizationAuditsUseCase: OrganizationAuditsUseCase,
    private val openMapUseCase: OpenMapUseCase
) : AbstractInputViewModel<Organization, OrganizationAuditsScreenState, OrganizationAuditsScreenEffect>(),
    UseOrganizationAuditsScreen {


    override fun createInitialState() = OrganizationAuditsScreenState()

    override fun setup(
        data: Organization,
    ) {
        emitState {
            OrganizationAuditsScreenState(
                id = data.id,
                organizationName = data.name,
                organizationAddress = data.address,
                imageUrl = data.imageUrl,
                latitude = data.latitude,
                longitude = data.longitude,
            )
        }
        refresh()
    }

    private fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            organizationAuditsUseCase(currentState.id).onSuccess { audits ->
                emitState {
                    it.copy(
                        audits = audits,
                        screen = it.screen.copy(state = ScreenState.SUCCESS)
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OrganizationAuditsScreenEffect.OnBack)
    }

    override fun handle(event: OrganizationAuditsScreenEvent) {
        onBackground {
            when (event) {
                is OrganizationAuditsScreenEvent.OnMap -> openMapUseCase(
                    currentState.latitude,
                    currentState.longitude
                )

                is OrganizationAuditsScreenEvent.OnAuditDetails -> postEffect(
                    OrganizationAuditsScreenEffect.OnChecklist(
                        AuditDocument(
                            placeId = currentState.id,
                            checklistId = event.audit.id,
                            name = event.audit.name,
                            documentId = null
                        ),
                        ChecklistStatus.CAN_BE_STARTED
                    )
                )
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}