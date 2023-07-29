package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.chatinput.ChatInput
import com.progressterra.ipbandroidview.features.chatinput.ChatInputState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.messages.Messages
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

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
                title = stringResource(R.string.support),
                showBackButton = true,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateColumn(
            state = state.screenState,
            useComponent = useComponent
        ) {
            Messages(
                state = state.messages,
            )
            ChatInput(
                state = state.input,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun SupportScreenPreview() {
    IpbTheme {
        SupportScreen(
            state = SupportScreenState(
                input = ChatInputState(
                    input = TextFieldState(text = "Some input")
                ),
                messages = MessagesState(
                    items = listOf(
                        MessagesState.Item(
                            user = true,
                            content = "Hello world!",
                            date = "12.12.2012"
                        ),
                        MessagesState.Item(
                            user = false,
                            content = "Hello, user!",
                            date = "12.12.2012"
                        ),
                        MessagesState.Item(
                            user = true,
                            content = "Mucho gusto!",
                            date = "12.12.2012"
                        )
                    )
                ),
                screenState = ScreenState.SUCCESS
            ),
            useComponent = UseSupportScreen.Empty()
        )
    }
}
