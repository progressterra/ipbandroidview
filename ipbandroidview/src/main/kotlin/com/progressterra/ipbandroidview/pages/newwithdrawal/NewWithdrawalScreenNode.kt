package com.progressterra.ipbandroidview.pages.newwithdrawal

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class NewWithdrawalScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<NewWithdrawalScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects {
            when (it) {
                is NewWithdrawalScreenEvent.Back -> onBack()
                is NewWithdrawalScreenEvent.Toast -> Toast.makeText(
                    context,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        NewWithdrawalScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}