package com.progressterra.ipbandroidview.pages.connections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.nav.OnDatingProfile
import org.koin.androidx.compose.getViewModel

interface ConnectionsScreenNavigation : OnDatingProfile

@Suppress("unused")
class ConnectionsScreenNode(
    buildContext: BuildContext,
    private val navigation: ConnectionsScreenNavigation
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ConnectionsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is ConnectionsScreenEffect.OnProfile -> navigation.onDatingProfile(effect.user)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        ConnectionsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}