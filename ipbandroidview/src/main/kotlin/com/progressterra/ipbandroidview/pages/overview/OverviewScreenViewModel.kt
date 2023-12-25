package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.checklist.FetchArchivedAuditsUseCase
import com.progressterra.ipbandroidview.processes.checklist.FetchOngoingAuditsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OverviewScreenViewModel(
    private val fetchOngoingAuditsUseCase: FetchOngoingAuditsUseCase,
    private val fetchArchivedAuditsUseCase: FetchArchivedAuditsUseCase
) : AbstractNonInputViewModel<OverviewState, OverviewEffect>(), UseOverviewScreen {

    override fun createInitialState() = OverviewState()


    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: OverviewEvent) {
        when (event) {
            is OverviewEvent.OnChecklist -> {
                postEffect(
                    OverviewEffect.OnChecklist(
                        AuditDocument(
                            checklistId = event.data.checklistId,
                            placeId = event.data.placeId,
                            documentId = event.data.documentId,
                            name = event.data.name
                        ) to if (event.data.finishDate != null) ChecklistStatus.READ_ONLY else ChecklistStatus.ONGOING
                    )
                )
            }

            is OverviewEvent.UpdateOngoingCounter -> postEffect(
                OverviewEffect.UpdateOngoingCounter(event.counter)
            )
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }


    override fun refresh() {
        onBackground {
            var isSuccess = true
            fetchArchivedAuditsUseCase().onSuccess { flow ->
                emitState { it.copy(archived = cachePaging(flow)) }
            }.onFailure {
                isSuccess = false
            }
            fetchOngoingAuditsUseCase().onSuccess { flow ->
                emitState { it.copy(ongoing = cachePaging(flow)) }
            }.onFailure {
                isSuccess = false
            }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }
}