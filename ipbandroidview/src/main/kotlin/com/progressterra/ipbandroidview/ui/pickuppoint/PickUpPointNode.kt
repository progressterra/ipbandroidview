package com.progressterra.ipbandroidview.ui.pickuppoint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.delivery.PickUpPointInfo
import com.progressterra.ipbandroidview.ui.order.OrderViewModel
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
        val parentViewModel: OrderViewModel = getViewModel()
        val viewModel: PickUpPointViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is PickUpPointEffect.Back -> onBack()
                is PickUpPointEffect.Next -> {
                    parentViewModel.updatePickUpPoint(it.info)
                    onBack()
                }
            }
        }
        var alreadyLaunched by rememberSaveable(points) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setPoints(points)
        }
        val state = viewModel.collectAsState()
        PickUpPointScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}