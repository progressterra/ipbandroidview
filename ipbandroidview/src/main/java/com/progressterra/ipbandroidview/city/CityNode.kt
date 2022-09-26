package com.progressterra.ipbandroidview.city

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class CityNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CityViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                CityEffect.Back -> onBack()
                CityEffect.Next -> onNext()
                CityEffect.Skip -> onSkip()
            }
        }
        val state = viewModel.collectAsState().value
        CityScreen(state = state, interactor = viewModel)
    }
}