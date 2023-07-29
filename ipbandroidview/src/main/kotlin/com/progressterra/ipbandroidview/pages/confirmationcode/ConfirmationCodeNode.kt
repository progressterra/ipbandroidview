package com.progressterra.ipbandroidview.pages.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

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
        viewModel.collectEffects {
            when (it) {
                is ConfirmationCodeEvent.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is ConfirmationCodeEvent.Back -> onBack()
                is ConfirmationCodeEvent.Next -> onNext()
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh(phoneNumber)
        }
        val state = viewModel.state.collectAsState().value
        ConfirmationCodeScreen(
            state = state, useComponent = viewModel
        )
    }
}