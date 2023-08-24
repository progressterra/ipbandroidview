package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisRequestsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onRequestDetails: (Document) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisRequestsViewModel>()
        viewModel.collectEffects {
            when (it) {
                is WantThisRequestsEvent.Back -> onBack()
                is WantThisRequestsEvent.RequestDetails -> onRequestDetails(it.document)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        WantThisRequests(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}