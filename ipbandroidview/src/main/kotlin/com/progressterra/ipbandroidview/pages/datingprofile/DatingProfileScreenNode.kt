package com.progressterra.ipbandroidview.pages.datingprofile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.DatingUser
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DatingProfileScreenNode(
    buildContext: BuildContext,
    private val input: DatingUser,
    private val navigation: DatingProfileScreenNavigation
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DatingProfileScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is DatingProfileScreenEffect.OnBack -> navigation.onBack()
                is DatingProfileScreenEffect.OnChat -> navigation.onChat(effect.id)
                is DatingProfileScreenEffect.OnSettings -> navigation.onSettings()
            }
        }
        val state = viewModel.state.collectAsState().value
        val focusManager = LocalFocusManager.current
        LaunchedEffect(input) {
            focusManager.clearFocus()
            viewModel.setup(input)
            viewModel.refresh()
        }
        DatingProfileScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}