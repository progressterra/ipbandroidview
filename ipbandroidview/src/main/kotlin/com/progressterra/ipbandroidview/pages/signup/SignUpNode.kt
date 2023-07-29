package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SignUpNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit,
    private val onBack: () -> Unit,
    private val openPhoto: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SignUpViewModel>()
        viewModel.collectEffects {
            when (it) {
                is SignUpEvent.OnBack -> onBack()
                is SignUpEvent.OnNext -> onNext()
                is SignUpEvent.OnSkip -> onSkip()
                is SignUpEvent.OpenPhoto -> openPhoto(it.photo)

            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        SignUpScreen(
            state = state, useComponent = viewModel
        )
    }
}