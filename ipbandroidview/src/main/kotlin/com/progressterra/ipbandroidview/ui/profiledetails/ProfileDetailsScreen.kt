package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.Button
import com.progressterra.ipbandroidview.composable.component.ButtonStyle
import com.progressterra.ipbandroidview.composable.component.TextField
import com.progressterra.ipbandroidview.theme.AppTheme

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
                .padding(AppTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.medium),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    id = "name",
                    state = state.name,
                    useComponent = interactor
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
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
    }
}