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
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

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
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.name,
                    hint = stringResource(id = R.string.name_surname),
                    onChange = { interactor.editName(it) }
                )
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.email,
                    hint = stringResource(id = R.string.email),
                    onChange = { interactor.editEmail(it) }
                )
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.phone,
                    hint = stringResource(id = R.string.phone_number),
                    enabled = false
                )
            }
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.confirmChange() },
                text = stringResource(id = R.string.confirm_change),
            )
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.logout() },
                text = stringResource(id = R.string.logout),
                textColor = AppTheme.colors.error,
                tint = AppTheme.colors.surfaces
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