package com.progressterra.ipbandroidview.pages.withdrawal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WithdrawalScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onCreate: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WithdrawalScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is WithdrawalScreenEvent.Back -> onBack()
                is WithdrawalScreenEvent.New -> onCreate()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        WithdrawalScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}