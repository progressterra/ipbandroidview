package com.progressterra.ipbandroidview.ui.organizationaudits

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.core.StartActivityContract
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.ui.organizations.Organization
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class OrganizationAuditsViewModel(
    private val organizationAuditsUseCase: OrganizationAuditsUseCase,
    private val startActivityContract: StartActivityContract.Client
) : ViewModel(), ContainerHost<OrganizationAuditsState, OrganizationAuditsEffect> {

    override val container: Container<OrganizationAuditsState, OrganizationAuditsEffect> =
        container(
            OrganizationAuditsState()
        )

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

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        organizationAuditsUseCase.organizationsAudits(state.id).onSuccess {
            reduce { state.copy(audits = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    fun onMapClick() = intent {
        val mapIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("geo:${state.latitude},${state.longitude}"))
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivityContract.start(mapIntent)
    }

    fun back() = intent {
        postSideEffect(OrganizationAuditsEffect.Back)
    }

    fun auditDetails(audit: OrganizationAudit) = intent {
        postSideEffect(
            OrganizationAuditsEffect.OpenChecklist(
                AuditDocument(
                    placeId = state.id,
                    checklistId = audit.id,
                    readOrCompleteOnly = false,
                    name = audit.name,
                    ongoing = false,
                    documentId = null
                )
            )
        )
    }
}