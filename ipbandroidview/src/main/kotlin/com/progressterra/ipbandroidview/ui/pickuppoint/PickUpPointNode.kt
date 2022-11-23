package com.progressterra.ipbandroidview.ui.pickuppoint

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class PickUpPointNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: PickUpPointViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                PickUpPointEffect.Back -> onBack()
                PickUpPointEffect.Next -> onNext()
            }
        }
        val state = viewModel.collectAsState()
        PickUpPointScreen(
            state = state::value,
            back = viewModel::back,
            editAddress = viewModel::editAddress,
            onMapClick = viewModel::onMapClick,
            onSuggestion = viewModel::onSuggestion,
            choose = viewModel::choose
        )
    }
}