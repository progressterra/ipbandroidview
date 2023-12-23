package com.progressterra.ipbandroidview.pages.newwithdrawal

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class NewWithdrawalScreenNode(
    buildContext: BuildContext,
    private val navigation: NewWithdrawalScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<NewWithdrawalScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is NewWithdrawalScreenEffect.Back -> navigation.onBack()
                is NewWithdrawalScreenEffect.Toast -> Toast.makeText(
                    context,
                    effect.data,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        NewWithdrawalScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}