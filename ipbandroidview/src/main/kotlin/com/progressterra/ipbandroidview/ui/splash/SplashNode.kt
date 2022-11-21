package com.progressterra.ipbandroidview.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SplashNode(
    buildContext: BuildContext,
    private val onAuth: () -> Unit,
    private val onMain: () -> Unit,
    private val settings: SplashSettings,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SplashViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                SplashEffect.OpenAuth -> onAuth()
                SplashEffect.OpenMain -> onMain()
            }
        }
        val state = viewModel.collectAsState()
        SplashScreen(state = state::value, settings = settings)
    }
}