package com.progressterra.ipbandroidview.pages.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SignInNode(
    buildContext: BuildContext,
    private val onNext: (phoneNumber: String) -> Unit,
    private val onSkip: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignInViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectEffects {
            when (it) {
                is SignInEffect.Next -> onNext(it.phoneNumber)
                is SignInEffect.Skip -> onSkip.invoke()
                is SignInEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        SignInScreen(state = state, useComponent = viewModel)
    }
}