package com.progressterra.ipbandroidview.pages.wantthisdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisDetailsScreenNode(
    buildContext: BuildContext,
    navigation: WantThisDetailsScreenNavigation,
    input: Document
) : AbstractInputNode<Document, WantThisDetailsScreenNavigation, WantThisDetailsScreenState, WantThisDetailsScreenEffect, WantThisDetailsScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: WantThisDetailsScreenEffect) {
        when (effect) {
            is WantThisDetailsScreenEffect.Back -> navigation.onBack()
            is WantThisDetailsScreenEffect.OpenPhoto -> navigation.openPhoto(effect.image)
            is WantThisDetailsScreenEffect.GoodsDetails -> navigation.openGoodsDetails(effect.id)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<WantThisDetailsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: WantThisDetailsScreenState) {
        WantThisDetailsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

