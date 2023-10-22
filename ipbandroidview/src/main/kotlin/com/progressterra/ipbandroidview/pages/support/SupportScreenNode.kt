package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SupportScreenNode(
    buildContext: BuildContext,
    private val navigation: SupportScreenNavigation
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SupportScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is SupportScreenEffect.OnBack -> navigation.onBack()
                is SupportScreenEffect.OnNext -> navigation.onChat(it.id)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        SupportScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}