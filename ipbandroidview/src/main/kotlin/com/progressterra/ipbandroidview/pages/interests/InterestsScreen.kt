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
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.features.interestspicker.InterestsPicker
import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

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
                    allInterests =
                    listOf(
                        Interest(
                            id = "0",
                            name = "coffee", picked = false
                        ), Interest(
                            id = "1",
                            name = "sport", picked = true
                        ), Interest(
                            id = "2",
                            name = "tea", picked = false
                        ), Interest(
                            id = "3",
                            name = "lalala", picked = true
                        ), Interest(
                            id = "4",
                            name = "some very long interest like a small dog psychology",
                            picked = false
                        ), Interest(
                            id = "5",
                            name = "armenian coffee", picked = false
                        ), Interest(
                            id = "6",
                            name = "argentinian coffee", picked = true
                        ), Interest(
                            id = "7",
                            name = "colombian coffee", picked = false
                        )
                    )
                )
            ), useComponent = UseInterestsScreen.Empty()
        )
    }
}

