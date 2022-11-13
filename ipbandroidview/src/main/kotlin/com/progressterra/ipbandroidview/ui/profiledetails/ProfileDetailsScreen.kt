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
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ProfileDetailsScreen(
    state: () -> ProfileDetailsState,
    confirmChange: () -> Unit,
    editEmail: (String) -> Unit,
    editName: (String) -> Unit,
    back: () -> Unit,
    logout: () -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = { stringResource(id = R.string.information) },
            onBack = back
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.medium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.large),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.large)
            ) {
                ThemedTextField(modifier = Modifier.fillMaxWidth(),
                    text = state()::name,
                    hint = { stringResource(id = R.string.name_surname) },
                    onChange = { editName(it) })
                ThemedTextField(modifier = Modifier.fillMaxWidth(),
                    text = state()::email,
                    hint = { stringResource(id = R.string.email) },
                    onChange = { editEmail(it) })
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state()::phone,
                    hint = { stringResource(id = R.string.phone_number) },
                    enabled = { false }
                )
            }
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = confirmChange,
                text = { stringResource(id = R.string.confirm_change) },
            )
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = logout,
                text = { stringResource(id = R.string.logout) },
                textColor = { AppTheme.colors.error },
                tint = { AppTheme.colors.surfaces }
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