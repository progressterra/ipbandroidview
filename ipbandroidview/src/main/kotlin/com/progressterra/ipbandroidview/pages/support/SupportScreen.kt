package com.progressterra.ipbandroidview.pages.support

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.features.supportchat.SupportChat
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.coroutines.flow.flowOf

@Composable
fun SupportScreen(
    modifier: Modifier = Modifier, state: SupportScreenState, useComponent: UseSupportScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = state.current.title, showBackButton = true, useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen, useComponent = useComponent
        ) {

            val lazyItems = state.current.subCategories.collectAsLazyPagingItems()
            LazyColumn(
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = lazyItems.itemCount, key = lazyItems.itemKey { it.id }) { index ->
                    lazyItems[index]?.let {
                        SupportChat(state = it, useComponent = useComponent)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SupportScreenPreview() {
    IpbTheme {
        SupportScreen(
            state = SupportScreenState(
                current = SupportChatState(
                    title = "Служба поддержки", subCategories = flowOf(
                        PagingData.from(
                            listOf(
                                SupportChatState(title = "Заказ 1234"),
                                SupportChatState(title = "Заказ 1234"),
                                SupportChatState(title = "Заказ 1234"),
                                SupportChatState(title = "Заказ 1234")
                            )
                        )
                    )
                ), screen = StateColumnState(state = ScreenState.SUCCESS)
            ), useComponent = UseSupportScreen.Empty()
        )
    }
}
