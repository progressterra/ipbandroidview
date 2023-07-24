package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
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
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
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
        StateColumn(
            state = state.screen, useComponent = useComponent,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                ProfileButton(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    state = state.requests,
                    useComponent = useComponent,
                    title = stringResource(R.string.past_requests)
                )
                if (!state.photo.isEmpty()) {
                    DocumentPhoto(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        state = state.photo,
                        useComponent = useComponent
                    )
                }
            }
            Column(
                modifier = Modifier.padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    val density = LocalDensity.current
                    var height by remember { mutableStateOf(0.dp) }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .onGloballyPositioned {
                                height = with(density) { it.size.height.toDp() }
                            },
                        state = state.send,
                        useComponent = useComponent,
                        title = stringResource(R.string.send_request)
                    )
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(height),
                        state = state.cancel,
                        useComponent = useComponent,
                        title = stringResource(R.string.cancel)
                    )
                }
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
