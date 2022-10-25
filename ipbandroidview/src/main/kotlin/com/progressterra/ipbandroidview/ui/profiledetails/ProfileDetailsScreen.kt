package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ProfileDetailsScreen(state: ProfileDetailsState, interactor: ProfileDetailsInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.information),
            onBack = { interactor.back() }
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
                    .background(AppTheme.colors.surfaces)
                    .padding(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.name,
                    hint = stringResource(id = R.string.name_surname),
                    onChange = { interactor.onName(it) }
                )
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.email,
                    hint = stringResource(id = R.string.email),
                    onChange = { interactor.onEmail(it) }
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
        ProfileDetailsScreen(
            ProfileDetailsState("+89994442345", "Канье Вест", ""),
            ProfileDetailsInteractor.Empty()
        )
    }
}