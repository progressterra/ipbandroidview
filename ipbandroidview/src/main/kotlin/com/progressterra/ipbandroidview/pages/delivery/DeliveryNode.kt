package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class DeliveryNode(
    buildContext: BuildContext,
    private val onPickUpPoint: (List<PickUpPointInfo>) -> Unit,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DeliveryViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is DeliveryEvent.Back -> onBack()
                is DeliveryEvent.Next -> onNext()
                is DeliveryEvent.SelectPickupPoint -> onPickUpPoint(it.points)
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        DeliveryScreen(
            state = state,
            useComponent = viewModel
        )
    }
}