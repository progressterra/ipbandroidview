package com.progressterra.ipbandroidview.ui.support

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.ChatInput
import com.progressterra.ipbandroidview.composable.component.ChatMessage
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SupportScreen(
    state: () -> SupportState,
    back: () -> Unit,
    editMessage: (String) -> Unit,
    send: () -> Unit
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(
                title = stringResource(R.string.support),
                onBack = back
            )
        },
        bottomBar = {
            ChatInput(
                modifier = Modifier.padding(horizontal = AppTheme.dimensions.small),
                editMessage = editMessage,
                message = state()::message,
                onSend = send
            )
        }
    ) { _, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(AppTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            items(state().messages) {
                ChatMessage(
                    message = { it }
                )
            }
        }
    }
}