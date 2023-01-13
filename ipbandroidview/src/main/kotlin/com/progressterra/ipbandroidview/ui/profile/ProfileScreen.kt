package com.progressterra.ipbandroidview.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.EditIcon
import com.progressterra.ipbandroidview.composable.ForwardIcon
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ProfileScreen(
    state: ProfileState,
    interactor: ProfileInteractor,
    settings: ProfileSettings
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.profile)
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .niceClickable(interactor::openDetails)
                    .padding(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
                    Text(
                        text = state.name.ifBlank { stringResource(id = R.string.set_name) },
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.title
                    )
                    Text(
                        text = state.phone,
                        color = AppTheme.colors.gray2,
                        style = AppTheme.typography.secondaryText
                    )
                }
                IconButton(onClick = interactor::openDetails) {
                    EditIcon()
                }
            }
            if (settings.showOrders)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .niceClickable(interactor::onOrders)
                        .padding(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.your_orders),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.text
                    )
                    IconButton(onClick = interactor::onOrders) {
                        ForwardIcon()
                    }
                }
            if (settings.showFavorites)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .niceClickable(interactor::onFavorites)
                        .padding(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.favorites),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.text
                    )
                    IconButton(onClick = interactor::onFavorites) {
                        ForwardIcon()
                    }
                }
            if (settings.showSupport)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .niceClickable(interactor::onSupport)
                        .padding(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.support),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.text
                    )
                    IconButton(onClick = interactor::onSupport) {
                        ForwardIcon()
                    }
                }
            if (settings.showReferral)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .niceClickable(interactor::onReferral)
                        .padding(AppTheme.dimensions.medium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.referral_program),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.text
                    )
                    IconButton(onClick = interactor::onReferral) {
                        ForwardIcon()
                    }
                }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
    }
}