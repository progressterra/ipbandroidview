package com.progressterra.ipbandroidview.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class SplashNode(
    buildContext: BuildContext,
    private val onReady: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SplashViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                SplashEffect.Ready -> onReady()
            }
        }
        val state = viewModel.collectAsState().value
        SplashScreen(state = state)
    }
}