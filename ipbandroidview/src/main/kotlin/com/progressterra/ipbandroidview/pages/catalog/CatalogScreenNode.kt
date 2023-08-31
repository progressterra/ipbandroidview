package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class CatalogScreenNode(
    buildContext: BuildContext, navigation: CatalogScreenNavigation
) : AbstractNonInputNode<CatalogScreenNavigation, CatalogScreenState, CatalogScreenEffect, CatalogViewModel>(
    buildContext, navigation
) {

    override fun mapEffect(effect: CatalogScreenEffect) {
        when (effect) {
            is CatalogScreenEffect.OnItem -> navigation.openGoodsDetails(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<CatalogViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: CatalogScreenState) {
        CatalogScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}