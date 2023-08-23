package com.progressterra.ipbandroidview.pages.interests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.interestspicker.InterestsPicker
import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun InterestsScreen(
    modifier: Modifier = Modifier, state: InterestsScreenState, useComponent: UseInterestsScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
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
            state = state.screen, useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BrushedText(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 36.dp),
                text = stringResource(R.string.choose_interests),
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                textAlign = TextAlign.Center
            )
            InterestsPicker(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                state = state.interests, useComponent = useComponent
            )
        }
    }
}

@Composable
@Preview
private fun InterestsScreenPreview() {
    IpbTheme {
        InterestsScreen(
            state = InterestsScreenState(
                screen = StateColumnState(state = ScreenState.SUCCESS),
                interests = InterestsPickerState(
                    items = listOf(
                        InterestsPickerState.Item(
                            name = "coffee", selected = false
                        ), InterestsPickerState.Item(
                            name = "sport", selected = true
                        ), InterestsPickerState.Item(
                            name = "tea", selected = false
                        ), InterestsPickerState.Item(
                            name = "lalala", selected = true
                        ), InterestsPickerState.Item(
                            name = "some very long interest like a small dog psychology",
                            selected = false
                        ), InterestsPickerState.Item(
                            name = "armenian coffee", selected = false
                        ), InterestsPickerState.Item(
                            name = "argentinian coffee", selected = true
                        ), InterestsPickerState.Item(
                            name = "colombian coffee", selected = false
                        )
                    )
                )
            ), useComponent = UseInterestsScreen.Empty()
        )
    }
}

