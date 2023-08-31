package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class FavoritesScreenNode(
    buildContext: BuildContext,
    navigation: FavoritesScreenNavigation
) : AbstractNonInputNode<FavoritesScreenNavigation, FavoritesScreenState, FavoritesScreenEffect, FavoritesScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: FavoritesScreenEffect) {
        when (effect) {
            is FavoritesScreenEffect.GoodsDetails -> navigation.openGoodsDetails(effect.data)
            is FavoritesScreenEffect.Back -> navigation.onBack()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<FavoritesScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: FavoritesScreenState) {
        FavoritesScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}