package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.checklist.Organization
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrganizationsNode(
    private val onOrganization: (Organization) -> Unit,
    buildContext: BuildContext
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrganizationsViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is OrganizationsEffect.OpenOrganization -> onOrganization(it.organization)
            }
        }
        val state = viewModel.collectAsState()
        OrganizationsScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}