package com.progressterra.ipbandroidview.pages.wantthisdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toCanBeEditted
import com.progressterra.ipbandroidview.entities.toColor
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChat
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhoto
import com.progressterra.ipbandroidview.features.storecard.StoreCard
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
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
    },
        bottomBar = {
            if (state.document.status.toCanBeEditted()) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 36.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.apply,
                        useComponent = useComponent,
                        title = stringResource(R.string.ready)
                    )
                }
            }
        }) { _, _ ->
        val scrollState = rememberScrollState()
        LaunchedEffect(state.chat.isVisible) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
        StateColumn(
            state = state.screen,
            scrollable = true,
            scrollState = scrollState,
            useComponent = useComponent,
            horizontalAlignment = Alignment.Start
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
                        modifier = Modifier.widthIn(max = 220.dp),
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
                        maxLines = 1,
                        tint = IpbTheme.colors.textTertiary.asBrush(),
                        style = IpbTheme.typography.footnoteRegular
                    )
                }
            }
            Column(
                modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.document.entries.forEach {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        useComponent = useComponent
                    )
                }
                DocumentPhoto(
                    state = state.document.photo,
                    useComponent = useComponent,
                    name = stringResource(id = R.string.want_this_photo)
                )
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

@Preview
@Composable
private fun WantThisDetailsScreenPreview() {
    IpbTheme {
        WantThisDetailsScreen(
            state = WantThisDetailsScreenState(
                screen = StateColumnState(state = ScreenState.SUCCESS),
                storeCard = StoreCardState(
                    name = "Ноутбук Lenovo IdeaPad 3 15ADA05",
                    price = SimplePrice(1000),
                    counter = CounterState("1", 5),
                    installment = Installment(
                        months = 4,
                        perMonth = SimplePrice(500)
                    )
                )
            ), useComponent = UseWantThisDetailsScreen.Empty()
        )
    }
}