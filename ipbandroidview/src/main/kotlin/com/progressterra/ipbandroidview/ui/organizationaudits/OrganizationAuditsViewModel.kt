package com.progressterra.ipbandroidview.ui.organizationaudits

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.core.startactivity.StartActivity
import com.progressterra.ipbandroidview.domain.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.ui.organizations.Organization
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrganizationAuditsViewModel(
    private val organizationAuditsUseCase: OrganizationAuditsUseCase,
    private val startActivity: StartActivity,
    private val checklistUseCase: ChecklistUseCase
) : ViewModel(), ContainerHost<OrganizationAuditsState, OrganizationAuditsEffect>,
    OrganizationAuditsInteractor {

    override val container: Container<OrganizationAuditsState, OrganizationAuditsEffect> =
        container(
            OrganizationAuditsState()
        )

    @Suppress("unused")
    fun setOrganization(
        organization: Organization,
    ) = intent {
        reduce {
            OrganizationAuditsState(
                id = organization.id,
                organizationName = organization.name,
                organizationAddress = organization.address,
                imageUrl = organization.imageUrl,
                latitude = organization.latitude,
                longitude = organization.longitude,
                warnings = organization.warnings
            )
        }
        organizationAuditsUseCase.organizationsAudits(state.id).onSuccess {
            reduce { state.copy(audits = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        organizationAuditsUseCase.organizationsAudits(state.id).onSuccess {
            reduce { state.copy(audits = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }


    override fun onMapClick() = intent {
        val mapIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("geo:${state.latitude},${state.longitude}"))
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity.startActivityFromIntent(mapIntent)
    }

    override fun onBack() = intent {
        postSideEffect(OrganizationAuditsEffect.OnBack)
    }


    override fun onAuditClick(organizationAudit: OrganizationAudit) = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        checklistUseCase.details(organizationAudit.id).onSuccess {
            postSideEffect(
                OrganizationAuditsEffect.OnChecklist(
                    Checklist(
                        placeId = state.id,
                        checklistId = organizationAudit.id,
                        name = organizationAudit.name,
                        repetitiveness = "PLACEHOLDER",
                        lastTimeChecked = organizationAudit.lastTime,
                        checks = it,
                        done = false,
                        ongoing = false,
                        documentId = null
                    )
                )
            )
            reduce { state.copy(screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }
}