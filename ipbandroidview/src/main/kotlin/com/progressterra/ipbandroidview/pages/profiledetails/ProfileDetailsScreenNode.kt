package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class ProfileDetailsScreenNode(
    buildContext: BuildContext,
    private val navigation: ProfileDetailsScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<ProfileDetailsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is ProfileDetailsScreenEffect.Back -> navigation.onBack()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        ProfileDetailsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}