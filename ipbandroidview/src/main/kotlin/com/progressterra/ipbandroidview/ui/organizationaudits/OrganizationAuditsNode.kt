package com.progressterra.ipbandroidview.ui.organizationaudits

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.Organization
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrganizationAuditsNode(
    buildContext: BuildContext,
    private val organization: Organization,
    private val onChecklist: (AuditDocument, ChecklistStatus) -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrganizationAuditsViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is OrganizationAuditsEffect.Back -> onBack()
                is OrganizationAuditsEffect.OpenChecklist -> onChecklist(
                    it.auditDocument,
                    it.initialStatus
                )
            }
        }
        var alreadyLaunched by rememberSaveable(organization) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setOrganization(organization)
        }
        val state = viewModel.collectAsState()
        OrganizationAuditsScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}
