package com.progressterra.ipbandroidview.pages.support

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Message
import com.progressterra.ipbandroidview.features.supportchat.SupportChat
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.messages.Messages
import com.progressterra.ipbandroidview.widgets.messages.MessagesState
import kotlinx.coroutines.flow.flowOf

@Composable
fun SupportScreen(
    modifier: Modifier = Modifier,
    state: SupportScreenState,
    useComponent: UseSupportScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = state.current.title,
                showBackButton = true,
                useComponent = useComponent
            )
        }, bottomBar = {
            if (state.current.finite)
                Row(
                    modifier = modifier.padding(
                        start = 20.dp,
                        top = 8.dp,
                        end = 20.dp,
                        bottom = 36.dp
                    )
                ) {
                    TextField(
                        modifier = modifier.fillMaxWidth(),
                        state = state.input,
                        useComponent = useComponent,
                        hint = stringResource(R.string.message),
                        actionIcon = R.drawable.ic_send
                    )
                }
        }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent
        ) {
            if (state.current.finite) {
                Messages(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    state = state.messages,
                    messagesBackground = IpbTheme.colors.surface.asBrush()
                )
            } else {
                val lazyItems = state.current.subCategories.collectAsLazyPagingItems()
                LazyColumn(
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        count = lazyItems.itemCount,
                        key = lazyItems.itemKey { it.id }
                    ) { index ->
                        lazyItems[index]?.let {
                            SupportChat(state = it, useComponent = useComponent)
                        }
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
                input = TextFieldState(text = "Some input"),
                messages = MessagesState(
                    items = flowOf(
                        PagingData.from(
                            listOf(
                                Message(
                                    id = "",
                                    user = true,
                                    content = "Hello world!",
                                    date = "12.12.2012"
                                ),
                                Message(
                                    id = "",
                                    user = false,
                                    content = "Hello, user!",
                                    date = "12.12.2012"
                                ),
                                Message(
                                    id = "",
                                    user = true,
                                    content = "Mucho gusto!",
                                    date = "12.12.2012"
                                )
                            )
                        )
                    )
                ),
                current = SupportChatState(
                    title = "Служба поддержки",
                    subCategories = flowOf(
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
            ),
            useComponent = UseSupportScreen.Empty()
        )
    }
}
