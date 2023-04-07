package com.progressterra.ipbandroidview.pages.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ConfirmationCodeNode(
    buildContext: BuildContext,
    private val phoneNumber: String,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ConfirmationCodeViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is ConfirmationCodeEvent.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ConfirmationCodeEvent.Back -> onBack()
                is ConfirmationCodeEvent.Next -> onNext()
            }
        }
        LaunchedEffect(phoneNumber) {
            viewModel.refresh(phoneNumber)
        }
        val state = viewModel.collectAsState()
        ConfirmationCodeScreen(
            state = state.value, useComponent = viewModel
        )
    }
}