package com.progressterra.ipbandroidview.pages.overview

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.checklist.FetchArchivedAuditsUseCase
import com.progressterra.ipbandroidview.processes.checklist.FetchOngoingAuditsUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OverviewScreenViewModel(
    private val fetchOngoingAuditsUseCase: FetchOngoingAuditsUseCase,
    private val fetchArchivedAuditsUseCase: FetchArchivedAuditsUseCase,
    private val manageResources: ManageResources
) : AbstractNonInputViewModel<OverviewState, OverviewEffect>(), UseOverviewScreen {

    override fun createInitialState() = OverviewState()


    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: OverviewEvent) {
        when (event) {
            is OverviewEvent.Archived -> postEffect(
                OverviewEffect.Archive(
                    title = manageResources.string(R.string.archived),
                    archived = event.documents
                )
            )

            is OverviewEvent.Complete -> postEffect(
                OverviewEffect.Archive(
                    title = manageResources.string(R.string.completed),
                    archived = event.documents
                )
            )

            is OverviewEvent.Ongoing -> postEffect(
                OverviewEffect.Archive(
                    title = manageResources.string(R.string.ongoing),
                    archived = event.documents
                )
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