package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CityNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit,
    private val settings: CitySettings
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CityViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                CityEffect.Back -> onBack()
                CityEffect.Next -> onNext()
            }
        }
        val state = viewModel.collectAsState()
        CityScreen(
            state = state.value,
            back = viewModel::back,
            skip = viewModel::skip,
            next = viewModel::next,
            editAddress = viewModel::editAddress,
            onMapClick = viewModel::onMapClick,
            onSuggestion = viewModel::onSuggestion,
            settings = settings
        )
    }
}