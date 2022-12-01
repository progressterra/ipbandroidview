package com.progressterra.ipbandroidview.ui.support

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SupportNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SupportViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is SupportEffect.Back -> onBack()
            }
        }
        val state = viewModel.collectAsState()
        SupportScreen(
            state = state::value,
            back = viewModel::back,
            editMessage = viewModel::editMessage,
            send = viewModel::sendMessage
        )
    }
}