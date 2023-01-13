package com.progressterra.ipbandroidview.ui.support

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ChatInput
import com.progressterra.ipbandroidview.composable.ChatMessage
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.model.Message
import com.progressterra.ipbandroidview.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SupportScreen(
    state: SupportState,
    interactor: SupportScreenInteractor
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(
                title = stringResource(R.string.support),
                onBack = interactor::onBack
            )
        },
        bottomBar = {
            ChatInput(
                modifier = Modifier.padding(horizontal = AppTheme.dimensions.small),
                editMessage = interactor::editMessage,
                message = state.message,
                onSend = interactor::sendMessage,
                enabled = state.screenState.isSuccess()
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = interactor::refresh
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(AppTheme.dimensions.small),
                verticalArrangement = Arrangement.spacedBy(
                    AppTheme.dimensions.small,
                    Alignment.Bottom
                ),
                reverseLayout = true
            ) {
                items(state.messages) {
                    ChatMessage(
                        modifier = Modifier.animateItemPlacement(),
                        message = it
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun SupportScreenPreview() {
    AppTheme {
        SupportScreen(
            state = SupportState(
                message = "Some unsent message",
                messages = listOf(
                    Message(
                        user = true,
                        content = "Some really cool message",
                        date = "123.123.23132"
                    ),
                    Message(
                        user = false,
                        content = "Some really cool message 123",
                        date = "123.123.23132"
                    ),
                    Message(
                        user = true,
                        content = "Some redfsdally cool message",
                        date = "123.123.2313r12"
                    ),
                    Message(
                        user = false,
                        content = "Some relskflnvsdfkally cool message",
                        date = "123.123.2313211"
                    ),
                    Message(
                        user = true,
                        content = "Some really cool messasdglepglrpgkrge",
                        date = "123.123.23132"
                    )
                )
            ),
            interactor = SupportScreenInteractor.Empty()
        )
    }
}