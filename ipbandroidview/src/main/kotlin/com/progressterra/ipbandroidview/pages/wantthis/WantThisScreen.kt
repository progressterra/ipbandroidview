package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhoto
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButton
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun WantThisScreen(
    modifier: Modifier = Modifier, state: WantThisScreenState, useComponent: UseWantThisScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.want_this),
            useComponent = useComponent,
            showBackButton = true
        )
    }) { _, _ ->
        StateBox(
            state = state.screen, useComponent = useComponent
        ) {
            Column(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                ProfileButton(
                    state = state.requests,
                    useComponent = useComponent,
                    title = stringResource(R.string.past_requests)
                )
                state.photo?.let {
                    Spacer(modifier = Modifier.height(20.dp))
                    DocumentPhoto(
                        state = it,
                        useComponent = useComponent
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(state.entries) {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            state = it,
                            useComponent = useComponent
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        state = state.send,
                        useComponent = useComponent,
                        title = stringResource(R.string.send_request)
                    )
                    Button(
                        modifier = Modifier.weight(1f),
                        state = state.cancel,
                        useComponent = useComponent,
                        title = stringResource(R.string.cancel)
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))

            }
        }
    }
}

@Composable
@Preview
private fun WantThisScreenPreview() {
    IpbTheme {
        WantThisScreen(
            state = WantThisScreenState(screen = ScreenState.SUCCESS),
            useComponent = UseWantThisScreen.Empty()
        )
    }
}
