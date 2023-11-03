package com.progressterra.ipbandroidview.pages.datingmain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DatingMainScreenNode(
    buildContext: BuildContext,
    private val navigation: DatingMainScreenNavigation
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DatingMainScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is DatingMainScreenEffect.OnFilter -> Unit
                is DatingMainScreenEffect.OnOwnProfile -> navigation.onOwnProfile()
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