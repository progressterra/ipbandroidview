package com.progressterra.ipbandroidview.pages.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.SignInData
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ConfirmationCodeScreenNode(
    buildContext: BuildContext,
    private val input: SignInData,
    private val navigation: ConfirmationCodeScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ConfirmationCodeScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is ConfirmationCodeScreenEffect.Toast -> {
                    Toast.makeText(context, effect.data, Toast.LENGTH_SHORT).show()
                }

                is ConfirmationCodeScreenEffect.Back -> navigation.onBack()
                is ConfirmationCodeScreenEffect.Next -> navigation.onSignUp()
            }
        }
        val state = viewModel.state.collectAsState().value
        val focusManager = LocalFocusManager.current
        LaunchedEffect(input) {
            focusManager.clearFocus()
            viewModel.setup(input)
        }
        ConfirmationCodeScreen(modifier = modifier, state = state, useComponent = viewModel)

    }
}