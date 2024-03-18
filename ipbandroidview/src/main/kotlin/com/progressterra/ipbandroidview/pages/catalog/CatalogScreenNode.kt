package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.shared.log
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class CatalogScreenNode(
    buildContext: BuildContext,
    private val navigation: CatalogScreenNavigation,
    private val customStoreCard: (@Composable (StoreCardState, UseStoreCard) -> Unit)? = null
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<CatalogScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is CatalogScreenEffect.OnItem -> navigation.onGoodsDetails(effect.data)
                is CatalogScreenEffect.OnAuth -> navigation.onAuth()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            if (!state.fetched) {
                log("CatalogScreenNode: Refreshing")
                viewModel.refresh()
            }
        }
        CatalogScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel,
            customStoreCard = customStoreCard
        )
    }
}