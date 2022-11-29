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
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.element.EditIcon
import com.progressterra.ipbandroidview.composable.element.ForwardIcon
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ProfileScreen(
    state: () -> ProfileState,
    openDetails: () -> Unit,
    onOrders: () -> Unit,
    onFavorites: () -> Unit
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
                    .padding(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
                    Text(
                        text = state().name.ifBlank { stringResource(id = R.string.set_name) },
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.title
                    )
                    Text(
                        text = state().phone,
                        color = AppTheme.colors.gray2,
                        style = AppTheme.typography.secondaryText
                    )
                }
                IconButton(onClick = openDetails) {
                    EditIcon()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .niceClickable(onOrders)
                    .padding(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.your_orders),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.text
                )
                IconButton(onClick = openDetails) {
                    ForwardIcon()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .niceClickable(onFavorites)
                    .padding(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.favorites),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.text
                )
                IconButton(onClick = openDetails) {
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