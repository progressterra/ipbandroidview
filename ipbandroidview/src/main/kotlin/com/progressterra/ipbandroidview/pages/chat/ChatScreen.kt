package com.progressterra.ipbandroidview.pages.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Message
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
fun ChatScreen(
    modifier: Modifier = Modifier, state: ChatScreenState, useComponent: UseChatScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(id = R.string.chat),
            showBackButton = true,
            useComponent = useComponent
        )
    }, bottomBar = {
        Row(
            modifier = Modifier.padding(
                start = 20.dp, top = 8.dp, end = 20.dp, bottom = 20.dp
            )
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                state = state.input,
                useComponent = useComponent,
                hint = stringResource(R.string.message)
            )
        }
    }) { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            Messages(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.messages,
                messagesBackground = IpbTheme.colors.surface.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    IpbTheme {
        ChatScreen(
            state = ChatScreenState(
                input = TextFieldState(text = "Some input"), messages = MessagesState(
                    items = flowOf(
                        PagingData.from(
                            listOf(
                                Message(
                                    id = "",
                                    user = true,
                                    content = "Hello world!",
                                    date = "12.12.2012"
                                ), Message(
                                    id = "",
                                    user = false,
                                    content = "Hello, user!",
                                    date = "12.12.2012"
                                ), Message(
                                    id = "",
                                    user = true,
                                    content = "Mucho gusto!",
                                    date = "12.12.2012"
                                )
                            )
                        )
                    )
                ), screen = StateColumnState(state = ScreenState.SUCCESS)
            ), useComponent = UseChatScreen.Empty()
        )
    }
}
