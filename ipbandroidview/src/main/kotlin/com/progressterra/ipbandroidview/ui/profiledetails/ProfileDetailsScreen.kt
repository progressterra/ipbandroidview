package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.composable.component.ButtonStyle
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

/**
 * name, email, phone - text field
 * confirm, logout - button
 */
@Composable
fun ProfileDetailsScreen(
    state: ProfileDetailsState,
    interactor: ProfileDetailsInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.information),
            onBack = { interactor.onBack() }
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(IpbTheme.shapes.medium)
                    .background(IpbTheme.colors.surfaces)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.name_why),
                    color = IpbTheme.colors.gray2,
                    style = IpbTheme.typography.secondary
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    id = "name",
                    state = state.name,
                    useComponent = interactor
                )
                Text(
                    text = stringResource(R.string.email_why),
                    color = IpbTheme.colors.gray2,
                    style = IpbTheme.typography.secondary
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    id = "email",
                    state = state.email,
                    useComponent = interactor
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    id = "phone",
                    state = state.phone,
                    useComponent = interactor
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                id = "confirm",
                state = state.confirm,
                useComponent = interactor
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                id = "logout",
                style = ButtonStyle.Error,
                state = state.logout,
                useComponent = interactor
            )
            Text(
                text = "${stringResource(R.string.version)} ${state.version}",
                color = IpbTheme.colors.gray2,
                style = IpbTheme.typography.secondary
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    IpbTheme {
        ProfileDetailsScreen(
            state = ProfileDetailsState(
                name = TextFieldState(
                    text = "John Doe"
                ),
                email = TextFieldState(
                    text = "dilanilka42@gmail.com"
                ),
                phone = TextFieldState(
                    text = "+380 99 999 99 99",
                    enabled = false
                ),
                confirm = ButtonState(
                    text = "Confirm"
                ),
                logout = ButtonState(
                    text = "Logout"
                )
            ),
            interactor = ProfileDetailsInteractor.Empty()
        )
    }
}