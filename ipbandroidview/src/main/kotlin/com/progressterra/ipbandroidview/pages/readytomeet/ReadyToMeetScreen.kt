package com.progressterra.ipbandroidview.pages.readytomeet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun ReadyToMeetScreen(
    modifier: Modifier = Modifier,
    state: ReadyToMeetScreenState,
    useComponent: UseReadyToMeetScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.status),
            showBackButton = true,
            useComponent = useComponent
        )
    }, bottomBar = {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.save,
                useComponent = useComponent,
                title = stringResource(R.string.save)
            )
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                state = state.skip,
                useComponent = useComponent,
                title = stringResource(R.string.skip_yet)
            )
        }
    }) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BrushedText(
                modifier = Modifier.padding(start = 34.dp, top = 34.dp, end = 34.dp),
                text = stringResource(R.string.ready_to_meet_invite),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    RadioButton(
                        selected = state.readyToMeet || state.user.readyToMeet,
                        onClick = { useComponent.handle(ReadyToMeetScreenEvent(true)) })
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        BrushedText(
                            modifier = Modifier.padding(start = 34.dp, top = 34.dp, end = 34.dp),
                            text = if (state.user.sex == Sex.MALE) stringResource(id = R.string.ready_male) else stringResource(
                                id = R.string.ready_female
                            ),
                            style = IpbTheme.typography.headline,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                        BrushedText(
                            modifier = Modifier.padding(start = 34.dp, top = 34.dp, end = 34.dp),
                            text = stringResource(id = R.string.ready_explained),
                            style = IpbTheme.typography.caption,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    RadioButton(
                        selected = !state.readyToMeet || !state.user.readyToMeet,
                        onClick = { useComponent.handle(ReadyToMeetScreenEvent(false)) })
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        BrushedText(
                            modifier = Modifier.padding(start = 34.dp, top = 34.dp, end = 34.dp),
                            text = if (state.user.sex == Sex.MALE) stringResource(id = R.string.not_ready_male) else stringResource(
                                id = R.string.not_ready_female
                            ),
                            style = IpbTheme.typography.headline,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                        BrushedText(
                            modifier = Modifier.padding(start = 34.dp, top = 34.dp, end = 34.dp),
                            text = stringResource(id = R.string.not_ready_explained),
                            style = IpbTheme.typography.caption,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                    }
                }
            }
            BrushedText(
                modifier = Modifier.padding(start = 34.dp, top = 54.dp, end = 54.dp),
                text = stringResource(R.string.ready_calm),
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }
}

