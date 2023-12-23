package com.progressterra.ipbandroidview.pages.locationpermission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class LocationPermissionScreenNode(
    buildContext: BuildContext,
    private val navigation: LocationPermissionNavigation
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<LocationPermissionScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is LocationPermissionScreenEffect.OnBack -> navigation.onBack()
                is LocationPermissionScreenEffect.OnSuccess -> navigation.onSuccess()
                is LocationPermissionScreenEffect.OnFailure -> navigation.onFailure()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        LocationPermissionScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}
