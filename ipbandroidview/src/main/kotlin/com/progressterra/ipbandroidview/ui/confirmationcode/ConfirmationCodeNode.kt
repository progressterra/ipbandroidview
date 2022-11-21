package com.progressterra.ipbandroidview.ui.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    private val onBack: () -> Unit,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ConfirmationCodeViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is ConfirmationCodeEffect.Next -> onNext()
                is ConfirmationCodeEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ConfirmationCodeEffect.Back -> onBack()
            }
        }
        var alreadyLaunched by rememberSaveable(phoneNumber) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setPhoneNumber(phoneNumber)
        }
        val state = viewModel.collectAsState()
        ConfirmationCodeScreen(
            state = state::value,
            resend = viewModel::resend,
            next = viewModel::next,
            editCode = viewModel::editCode,
            back = viewModel::back
        )
    }
}