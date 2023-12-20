package com.progressterra.ipbandroidview.pages.organizationaudits

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Organization
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrganizationAuditsNode(
    buildContext: BuildContext,
    private val input: Organization,
    private val navigation: OrganizationAuditsNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrganizationAuditsViewModel>()
        viewModel.collectEffects {
            when (it) {
                is OrganizationAuditsEffect.OnBack -> navigation.onBack()
                is OrganizationAuditsEffect.OnChecklist -> navigation.onChecklist(
                    it.auditDocument,
                    it.initialStatus
                )
            }
        }
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        val state = viewModel.state.collectAsState().value
        OrganizationAuditsScreen(state = state, useComponent = viewModel)
    }
}