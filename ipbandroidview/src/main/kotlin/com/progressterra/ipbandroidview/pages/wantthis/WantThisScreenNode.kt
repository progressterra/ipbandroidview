package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class WantThisScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onRequests: () -> Unit,
    private val onPhoto: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisScreenViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is WantThisScreenEvent.Back -> onBack()
                is WantThisScreenEvent.Requests -> onRequests()
                is WantThisScreenEvent.OpenPhoto -> onPhoto(it.url)
            }
        }
        val state = viewModel.collectAsState().value
        WantThisScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}