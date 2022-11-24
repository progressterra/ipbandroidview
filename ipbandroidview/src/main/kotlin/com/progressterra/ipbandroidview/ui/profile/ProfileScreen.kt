package com.progressterra.ipbandroidview.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ProfileScreen(state: () -> ProfileState, openDetails: () -> Unit) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.profile)
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.medium),
                horizontalArrangement = Arrangement.SpaceBetween
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
                //TODO replace all icons
                IconButton(onClick = openDetails) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = stringResource(
                            id = R.string.edit_profile
                        ), tint = AppTheme.colors.gray2
                    )
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