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
class SignInScreenNode(
    buildContext: BuildContext,
    private val navigation: SignInScreenNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SignInScreenViewModel>()
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        viewModel.collectEffects {
            when (it) {
                is SignInScreenEffect.Next -> navigation.onCodeConfirmation(it.data)
                is SignInScreenEffect.Skip -> navigation.onMain()
                is SignInScreenEffect.Toast -> Toast.makeText(
                    context,
                    it.data,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val state = viewModel.state.collectAsState().value
        SignInScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}