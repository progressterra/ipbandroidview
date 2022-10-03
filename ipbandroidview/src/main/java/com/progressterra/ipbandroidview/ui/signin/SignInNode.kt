package com.progressterra.ipbandroidview.ui.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SignInNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignInViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is SignInEffect.Back -> onBack()
                is SignInEffect.Next -> onNext()
                is SignInEffect.Skip -> onSkip()
                is SignInEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_LONG)
                    .show()
            }
        }
        val state = viewModel.collectAsState().value
        SignInScreen(state = state, interactor = viewModel)
    }
}