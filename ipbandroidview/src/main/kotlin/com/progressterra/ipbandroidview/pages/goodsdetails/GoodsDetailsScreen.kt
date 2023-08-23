package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.buygoods.BuyGoods
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescription
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGallery
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.galleries.Galleries

@Composable
fun GoodsDetailsScreen(
    state: GoodsDetailsState, useComponent: UseGoodsDetails
) {
    ThemedLayout(topBar = {
        TopBar(
            title = state.name, showBackButton = true, useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            scrollable = true,
            state = state.screen,
            useComponent = useComponent
        ) {
            ItemGallery(state = state.gallery, useComponent = useComponent)
            GoodsDescription(state = state.description, useComponent = useComponent)
            BuyGoods(state = state.buyGoods, useComponent = useComponent)
            Galleries(
                state = state.similarGoods, useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun GoodsDetailsScreenPreview() {
    val state = GoodsDetailsState(
        screen = StateBoxState(state = ScreenState.SUCCESS),
        name = "Красивый товар",
        gallery = ItemGalleryState(
            listOf(
                "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
            )
        ),
        description = GoodsDescriptionState(
            name = "Красивый товар",
            description = "Описание товара"
        )
    )
    GoodsDetailsScreen(state = state, useComponent = UseGoodsDetails.Empty())
}
