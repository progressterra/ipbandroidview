package com.progressterra.ipbandroidview.pages.avatarpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.avatarpicker.AvatarPicker
import com.progressterra.ipbandroidview.features.avatarpicker.AvatarPickerState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn

@Composable
fun AvatarPickerScreen(
    state: AvatarPickerScreenState, useComponent: UseAvatarPickerScreen
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.avatar_picker),
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
                state = state.confirm,
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
            Spacer(modifier = Modifier.height(36.dp))
            BrushedText(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.avatar_picker_desc),
                style = IpbTheme.typography.body,
                textAlign = TextAlign.Center,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            Spacer(modifier = Modifier.height(25.dp))
            AvatarPicker(
                state = state.avatars, useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun AvatarPickerScreenPreview() {
    val avatars = listOf(
        AvatarPickerState.Item(
            url = "https://example.com/avatar1.jpg", selected = true
        ), AvatarPickerState.Item(
            url = "https://example.com/avatar2.jpg", selected = false
        ), AvatarPickerState.Item(
            url = "https://example.com/avatar3.jpg", selected = false
        ), AvatarPickerState.Item(
            url = "https://example.com/avatar4.jpg", selected = false
        )
    )

    val avatarPickerState = AvatarPickerState(avatars)

    val avatarPickerScreenState = AvatarPickerScreenState(
        screen = ScreenState.SUCCESS, avatars = avatarPickerState
    )

    IpbTheme {
        AvatarPickerScreen(
            state = avatarPickerScreenState, useComponent = UseAvatarPickerScreen.Empty()
        )
    }
}
