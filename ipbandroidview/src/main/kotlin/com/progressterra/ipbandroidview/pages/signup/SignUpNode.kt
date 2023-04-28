package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.MultisizedImage
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SignUpNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit,
    private val onBack: () -> Unit,
    private val openPhoto: (MultisizedImage) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SignUpViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is SignUpEvent.OnBack -> onBack()
                is SignUpEvent.OnNext -> onNext()
                is SignUpEvent.OnSkip -> onSkip()
                is SignUpEvent.OpenPhoto -> openPhoto(it.photo)

            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        SignUpScreen(
            state = state, useComponent = viewModel
        )
    }
}