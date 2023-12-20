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

class OrganizationAuditsViewModel(
    private val organizationAuditsUseCase: OrganizationAuditsUseCase,
    private val openMapUseCase: OpenMapUseCase
) : AbstractInputViewModel<Organization, OrganizationAuditsState, OrganizationAuditsEffect>(),
    UseOrganizationAuditsScreen {


    override fun createInitialState() = OrganizationAuditsState()

    override fun setup(
        data: Organization,
    ) {
        emitState {
            OrganizationAuditsState(
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
        postEffect(OrganizationAuditsEffect.OnBack)
    }

    override fun handle(event: OrganizationAuditsEvent) {
        onBackground {
            when (event) {
                is OrganizationAuditsEvent.OnMap -> openMapUseCase(
                    currentState.latitude,
                    currentState.longitude
                )

                is OrganizationAuditsEvent.OnAuditDetails -> postEffect(
                    OrganizationAuditsEffect.OnChecklist(
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