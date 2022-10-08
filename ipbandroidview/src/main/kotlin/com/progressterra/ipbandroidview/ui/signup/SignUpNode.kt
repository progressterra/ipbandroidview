package com.progressterra.ipbandroidview.ui.signup

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.root.RootViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SignUpNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val rootViewModel: RootViewModel = getViewModel()
        rootViewModel.configure(onBack = onBack, title = stringResource(id = R.string.signup))
        val viewModel: SignUpViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is SignUpEffect.Next -> onNext()
                is SignUpEffect.Skip -> onSkip()
                is SignUpEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        val state = viewModel.collectAsState().value
        SignUpScreen(state = state, interactor = viewModel)
    }
}