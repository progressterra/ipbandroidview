package com.progressterra.ipbandroidview.pages.signin

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
    private val onNext: (phoneNumber: String) -> Unit,
    private val onSkip: (() -> Unit)? = null
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignInViewModel = getViewModel()
        viewModel.refresh(onSkip != null)
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is SignInEffect.Next -> onNext(it.phoneNumber)
                is SignInEffect.Skip -> onSkip?.invoke()
                is SignInEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val state = viewModel.collectAsState().value
        SignInScreen(state = state, useComponent = viewModel)
    }
}