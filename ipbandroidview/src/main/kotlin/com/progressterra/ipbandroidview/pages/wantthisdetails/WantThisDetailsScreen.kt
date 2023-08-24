package com.progressterra.ipbandroidview.pages.wantthisdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toColor
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChat
import com.progressterra.ipbandroidview.features.storecard.StoreCard
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun WantThisDetailsScreen(
    modifier: Modifier = Modifier,
    state: WantThisDetailsScreenState,
    useComponent: UseWantThisDetailsScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = state.document.name,
            showBackButton = true,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    BrushedText(
                        text = state.document.name,
                        style = IpbTheme.typography.title,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    BrushedText(
                        text = state.document.status.toString { stringResource(id = it) },
                        style = IpbTheme.typography.subHeadlineBold,
                        tint = state.document.status.toColor()
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { useComponent.handle(WantThisDetailsScreenEvent) }
                    ) {
                        BrushedIcon(
                            resId = R.drawable.ic_chat,
                            tint = IpbTheme.colors.iconTertiary.asBrush()
                        )
                    }
                    BrushedText(
                        text = stringResource(R.string.want_this_chat),
                        tint = IpbTheme.colors.textTertiary.asBrush(),
                        style = IpbTheme.typography.footnoteRegular
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(start = 20.dp, top = 8.dp, end = 20.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.document.entries) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        useComponent = useComponent
                    )
                }
            }
            if (!state.storeCard.isEmpty()) {
                StoreCard(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
                    state = state.storeCard,
                    useComponent = useComponent
                )
            }
            AttachableChat(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 8.dp),
                state = state.chat,
                canBeClosed = true,
                useComponent = useComponent
            )
        }
    }
}