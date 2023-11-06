package com.progressterra.ipbandroidview.pages.peoplenearby

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class PeopleNearbyNode(
    buildContext: BuildContext,
    private val navigation: PeopleNearbyScreenNavigation
) : Node(buildContext = buildContext) {


    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<PeopleNearbyScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is PeopleNearbyScreenEffect.OnProfile -> navigation.onDatingProfile(effect.user)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        PeopleNearbyScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}