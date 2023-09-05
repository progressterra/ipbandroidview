package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisRequestsScreenNode(
    buildContext: BuildContext,
    private val navigation: WantThisRequestsScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisRequestsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is WantThisRequestsScreenEffect.Back -> navigation.onBack()
                is WantThisRequestsScreenEffect.RequestDetails -> navigation.onRequestDetails(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        WantThisRequestsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}