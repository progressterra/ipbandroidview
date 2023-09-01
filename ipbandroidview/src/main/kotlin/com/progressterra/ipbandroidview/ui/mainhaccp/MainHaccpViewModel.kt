package com.progressterra.ipbandroidview.ui.mainhaccp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrganizationsOverviewEvent
import com.progressterra.ipbandroidview.composable.PartnerBlockEvent
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationsOverviewUseCase
import com.progressterra.ipbandroidview.domain.usecase.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.ext.toScreenState
import com.progressterra.ipbandroidview.model.Partner
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainHaccpViewModel(
    private val fetchPartnerUseCase: FetchPartnerUseCase,
    private val fetchOrganizationsOverviewUseCase: OrganizationsOverviewUseCase,
    private val manageResources: ManageResources
) : ViewModel(), ContainerHost<MainHaccpState, MainHaccpEffect>, MainHaccpInteractor {

    override val container: Container<MainHaccpState, MainHaccpEffect> = container(MainHaccpState())


    override fun onPartnerClick(partner: Partner) = intent {
        postSideEffect(MainHaccpEffect.OpenPartner(partner))
    }

    override fun handleEvent(event: OrganizationsOverviewEvent) = intent {
        when (event) {
            is OrganizationsOverviewEvent.Archived -> postSideEffect(
                MainHaccpEffect.Archive(
                    title = manageResources.string(R.string.archived),
                    archived = event.documents
                )
            )

            is OrganizationsOverviewEvent.Complete -> postSideEffect(
                MainHaccpEffect.Archive(
                    title = manageResources.string(R.string.completed),
                    archived = event.documents
                )
            )

            is OrganizationsOverviewEvent.Ongoing -> postSideEffect(
                MainHaccpEffect.Archive(
                    title = manageResources.string(R.string.ongoing),
                    archived = event.documents
                )
            )
        }
    }

    override fun handleEvent(event: PartnerBlockEvent) = intent {
        when (event) {
            is PartnerBlockEvent.PartnerClicked -> postSideEffect(MainHaccpEffect.OpenPartner(state.partner))
        }
    }

    override fun refresh() = intent {
        Log.d("HACCP", "refresh start")
        var isSuccess = true
        fetchPartnerUseCase().onSuccess {
            Log.d("HACCP", it.toString())
            reduce { state.copy(partner = it) }
        }.onFailure {
            Log.e("HACCP", it.message ?: "", it)
            isSuccess = false
        }
        fetchOrganizationsOverviewUseCase().onSuccess {
            Log.d("HACCP", it.toString())
            reduce { state.copy(overviews = it) }
        }.onFailure {
            Log.e("HACCP", it.message ?: "", it)
            isSuccess = false
        }
        reduce { state.copy(screenState = isSuccess.toScreenState()) }
    }
}