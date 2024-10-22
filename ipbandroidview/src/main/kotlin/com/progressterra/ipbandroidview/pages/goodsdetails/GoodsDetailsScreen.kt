package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.buygoods.BuyGoods
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescription
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionState
import com.progressterra.ipbandroidview.features.itemgallery.ItemGallery
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.Layout
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.galleries.Galleries

import androidx.compose.ui.graphics.Color
@Composable
fun GoodsDetailsScreen(
    modifier: Modifier = Modifier,
    state: GoodsDetailsScreenState,
    useComponent: UseGoodsDetailsScreen
) {
    Layout(
        modifier = modifier,
        topBar = { TopBar(title = state.name, showBackButton = true, useComponent = useComponent) }
    ) { _, _ ->
        StateColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            scrollable = true,
            state = state.screen,
            useComponent = useComponent
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            ItemGallery(state = state.gallery, useComponent = useComponent)

            if (state.idrfSpecification == IpbAndroidViewSettings.IDRFSPECIFICATION_FOR_TEACH) {
                Text(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                listOf(
                                    IpbAndroidViewSettings.GRADIENT_COLOR_LEFT,
                                    IpbAndroidViewSettings.GRADIENT_COLOR_RIGHT
                                )
                            )
                        )
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = "Кэшбэк " + state.buyGoods.price.toString() + " баллов",
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary2.asBrush(),
                )
            }


            GoodsDescription(state = state.description, useComponent = useComponent)
            if (state.idrfSpecification == IpbAndroidViewSettings.IDRFSPECIFICATION_FOR_BUY) {
                BuyGoods(state = state.buyGoods, useComponent = useComponent)

                Galleries(state = state.similarGoods, useComponent = useComponent)
            }
            else
            {

            }

        }
    }
}

@Preview
@Composable
private fun GoodsDetailsScreenPreview() {
    val state =
        GoodsDetailsScreenState(
            screen = StateColumnState(state = ScreenState.SUCCESS),
            name = "Красивый товар",
            gallery =
                ItemGalleryState(
                    listOf(
                        "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                        "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                        "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                        "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                        "https://images.unsplash.com/photo-1616489953148-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                    )
                ),
            description =
                GoodsDescriptionState(name = "Красивый товар", description = "Описание товара")
        )
    GoodsDetailsScreen(state = state, useComponent = UseGoodsDetailsScreen.Empty())
}
