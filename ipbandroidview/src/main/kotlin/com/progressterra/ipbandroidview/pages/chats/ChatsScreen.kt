package com.progressterra.ipbandroidview.pages.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.search.Search
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.widgets.chats.Chats
import com.progressterra.ipbandroidview.widgets.chats.ChatsState

@Composable
fun ChatsScreen(
    state: ChatsScreenState,
    useComponent: UseChatsScreen
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.messages),
                showBackButton = true,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Search(
                modifier = Modifier.padding(horizontal = 16.dp),
                state = state.search,
                useComponent = useComponent
            )
            Chats(
                state = state.chats,
                useComponent = useComponent
            )
        }
    }
}