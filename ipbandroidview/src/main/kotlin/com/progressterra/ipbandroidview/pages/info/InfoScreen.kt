package com.progressterra.ipbandroidview.pages.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.info.Info
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    state: InfoScreenState,
    useComponent: UseInfoScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.info_about_you),
                showBackButton = true,
                useComponent = useComponent
            )
        }, bottomBar = {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
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
            state = state.screen, useComponent = useComponent
        ) {
            Info(
                modifier = Modifier.padding(top = 36.dp, start = 16.dp, end = 16.dp),
                state = state.info,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun InfoScreenPreview() {
    IpbTheme {
        InfoScreen(
            state = InfoScreenState(screen = StateColumnState(state = ScreenState.SUCCESS)),
            useComponent = UseInfoScreen.Empty()
        )
    }
}
