package com.progressterra.ipbandroidview.pages.pfppicker

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
import com.progressterra.ipbandroidview.features.pfppicker.PfpPicker
import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statebox.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn

@Composable
fun PfpPickerScreen(
    modifier: Modifier = Modifier,
    state: PfpPickerScreenState,
    useComponent: UsePfpPickerScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.photo_picker),
                showBackButton = true,
                useComponent = useComponent
            )
        }, bottomBar = {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.choose,
                    useComponent = useComponent,
                    title = stringResource(R.string.choose)
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
            PfpPicker(
                modifier = Modifier.padding(top = 36.dp, start = 56.dp, end = 56.dp),
                state = state.pfpPicker,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun PfpPickerScreenPreviewEmpty() {
    IpbTheme {
        PfpPickerScreen(
            state = PfpPickerScreenState(screen = StateColumnState(state = ScreenState.SUCCESS)),
            useComponent = UsePfpPickerScreen.Empty()
        )
    }
}

@Preview
@Composable
private fun PfpPickerScreenPreview() {
    IpbTheme {
        PfpPickerScreen(
            state = PfpPickerScreenState(
                screen = StateColumnState(state = ScreenState.SUCCESS),
                pfpPicker = PfpPickerState(url = "")
            ),
            useComponent = UsePfpPickerScreen.Empty()
        )
    }
}