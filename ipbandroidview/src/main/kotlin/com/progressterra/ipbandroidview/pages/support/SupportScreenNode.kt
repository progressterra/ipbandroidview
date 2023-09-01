package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SupportScreenNode(
    buildContext: BuildContext,
    navigation: SupportScreenNavigation
) : AbstractNonInputNode<SupportScreenNavigation, SupportScreenState, SupportScreenEffect, SupportScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: SupportScreenEffect) {
        navigation.onBack()
    }

    @Composable
    override fun obtainViewModel() = getViewModel<SupportScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: SupportScreenState) {
        SupportScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}