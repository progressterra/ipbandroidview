package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.base.ConfigureScreen
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CityNode(
    buildContext: BuildContext,
    private val configureScreen: ConfigureScreen,
    private val onBack: () -> Unit,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        configureScreen.configure(onBack = onBack, title = stringResource(id = R.string.city))
        val viewModel: CityViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                CityEffect.Next -> onNext()
                CityEffect.Skip -> onSkip()
            }
        }
        val state = viewModel.collectAsState().value
        CityScreen(state = state, interactor = viewModel)
    }
}