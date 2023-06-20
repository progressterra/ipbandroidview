package com.progressterra.ipbandroidview.pages.pickuppoint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class PickUpPointNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val points: List<PickUpPointInfo>
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val parentViewModel: DeliveryViewModel = getViewModel()
        val viewModel: PickUpPointViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is PickUpPointEffect.Back -> onBack()
                is PickUpPointEffect.Next -> {
                    parentViewModel.uPickUpPoint(it.info)
                    onBack()
                }
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh(points)
        }
        val state = viewModel.collectAsState()
        PickUpPointScreen(
            state = state.value,
            useComponent = viewModel
        )
    }
}