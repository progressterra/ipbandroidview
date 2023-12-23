package com.progressterra.ipbandroidview.pages.organizations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrganizationsNode(
    buildContext: BuildContext,
    private val navigation: OrganizationsNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrganizationsViewModel>()
        viewModel.collectEffects {
            when (it) {
                is OrganizationsEffect.OnOrganization -> navigation.onOrganization(it.organization)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        OrganizationsScreen(state = state, useComponent = viewModel)
    }
}
