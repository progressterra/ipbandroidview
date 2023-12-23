package com.progressterra.ipbandroidview.pages.organizationaudits

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Organization
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class OrganizationAuditsScreenNode(
    buildContext: BuildContext,
    private val input: Organization,
    private val navigation: OrganizationAuditsScreenNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<OrganizationAuditsScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is OrganizationAuditsScreenEffect.OnBack -> navigation.onBack()
                is OrganizationAuditsScreenEffect.OnChecklist -> navigation.onChecklist(it.data)
            }
        }
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        val state = viewModel.state.collectAsState().value
        OrganizationAuditsScreen(state = state, useComponent = viewModel)
    }
}