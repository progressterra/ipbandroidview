package com.progressterra.ipbandroidview.pages.wantthis

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
class WantThisScreenNode(
    buildContext: BuildContext,
    private val navigation: WantThisScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is WantThisScreenEffect.Back -> navigation.onBack()
                is WantThisScreenEffect.Requests -> navigation.onWantThisRequests()
                is WantThisScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
                is WantThisScreenEffect.Toast -> Toast.makeText(
                    context,
                    effect.data,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        WantThisScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}