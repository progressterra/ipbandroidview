package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class BankCardDetailsScreenNode(
    buildContext: BuildContext,
    private val input: Document,
    private val navigation: BankCardDetailsScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<BankCardDetailsScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is BankCardDetailsScreenEffect.Back -> navigation.onBack()
                is BankCardDetailsScreenEffect.OpenPhoto -> navigation.onPhoto(effect.data)
                is BankCardDetailsScreenEffect.Toast -> Toast.makeText(
                    context,
                    effect.data,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        BankCardDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}