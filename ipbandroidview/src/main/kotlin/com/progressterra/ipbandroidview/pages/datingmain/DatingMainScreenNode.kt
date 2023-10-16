package com.progressterra.ipbandroidview.pages.datingmain

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.Lifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

class DatingMainScreenNode(
    buildContext: BuildContext,
    private val navigation: DatingMainScreenNavigation
) : Node(buildContext = buildContext) {

    override fun updateLifecycleState(state: Lifecycle.State) {
        super.updateLifecycleState(state)
        Log.d("DATING", "updateLifecycleState: ${state.name}")
    }

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DatingMainScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is DatingMainScreenEffect.OnFilter -> TODO()
                is DatingMainScreenEffect.OnProfile -> navigation.onDatingProfile(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        val focusManager = LocalFocusManager.current
        LaunchedEffect(Unit) {
            focusManager.clearFocus()
            viewModel.refresh()
        }
        DatingMainScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}