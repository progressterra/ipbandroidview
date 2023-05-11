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
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ChatInput
import com.progressterra.ipbandroidview.composable.ChatMessage
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SupportScreen(
    state: SupportState,
    interactor: SupportInteractor
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(
                title = stringResource(R.string.support),
                onBack = { interactor.onBack() }
            )
        },
        bottomBar = {
            ChatInput(
                modifier = Modifier.padding(horizontal = AppTheme.dimensions.small),
                state = state.chatInput,
                id = "main",
                useComponent = interactor
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = { interactor.refresh() }
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