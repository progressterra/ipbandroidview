package com.progressterra.ipbandroidview.pages.checklist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class ChecklistScreenNode(
    buildContext: BuildContext,
    private val input: Pair<AuditDocument, ChecklistStatus>,
    private val navigation: ChecklistScreenNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<ChecklistScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is ChecklistScreenEffect.OnBack -> navigation.onBack()
                is ChecklistScreenEffect.OnImage -> navigation.onPhoto(it.picture)
            }
        }
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        val state = viewModel.state.collectAsState().value
        ChecklistScreen(
            state = state,
            useComponent = viewModel
        )
    }
}