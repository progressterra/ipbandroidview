package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisRequestsScreenNode(
    buildContext: BuildContext,
    navigation: WantThisRequestsScreenNavigation
) : AbstractNonInputNode<WantThisRequestsScreenNavigation, WantThisRequestsScreenState, WantThisRequestsScreenEffect, WantThisRequestsScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: WantThisRequestsScreenEffect) {
        when (effect) {
            is WantThisRequestsScreenEffect.Back -> navigation.onBack()
            is WantThisRequestsScreenEffect.RequestDetails -> navigation.onRequestDetails(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<WantThisRequestsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: WantThisRequestsScreenState) {
        WantThisRequestsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}