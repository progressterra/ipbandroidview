package com.progressterra.ipbandroidview.pages.wantthisdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class WantThisDetailsScreenNode(
    buildContext: BuildContext,
    private val navigation: WantThisDetailsScreenNavigation,
    private val input: Document
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<WantThisDetailsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is WantThisDetailsScreenEffect.Back -> navigation.onBack()
                is WantThisDetailsScreenEffect.OpenPhoto -> navigation.onPhoto(effect.image)
                is WantThisDetailsScreenEffect.GoodsDetails -> navigation.onGoodsDetails(effect.id)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        WantThisDetailsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

