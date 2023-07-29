package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisRequestsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onGoodsDetails: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisRequestsViewModel>()
        viewModel.collectEffects {
            when (it) {
                is WantThisRequestsEvent.Back -> onBack()
                is WantThisRequestsEvent.GoodsDetails -> onGoodsDetails(it.id)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.value
        WantThisRequests(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}