package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class CartNode(
    buildContext: BuildContext,
    private val onItem: (String) -> Unit,
    private val onPayment: () -> Unit
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<CartViewModel>()
        viewModel.collectEffects {
            when (it) {
                is CartEvent.Payment -> onPayment()
                is CartEvent.OnItem -> onItem(it.id)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        CartScreen(
            state = state,
            useComponent = viewModel
        )
    }
}