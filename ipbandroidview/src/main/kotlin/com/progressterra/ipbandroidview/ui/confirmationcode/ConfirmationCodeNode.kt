package com.progressterra.ipbandroidview.ui.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.base.ConfigureScreen
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ConfirmationCodeNode(
    private val configureScreen: ConfigureScreen,
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        configureScreen.configure(
            onBack = onBack, title = stringResource(id = R.string.verification_code)
        )
        val viewModel: ConfirmationCodeViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is ConfirmationEffect.Next -> onNext()
                is ConfirmationEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        val state = viewModel.collectAsState().value
        ConfirmationCodeScreen(state = state, interactor = viewModel)
    }
}