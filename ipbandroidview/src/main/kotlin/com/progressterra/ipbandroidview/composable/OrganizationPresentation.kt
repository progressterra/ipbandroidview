package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.MapIcon
import com.progressterra.ipbandroidview.composable.SimpleImage
import com.progressterra.ipbandroidview.theme.AppTheme

private val picHeight = 188.dp

@Composable
fun OrganizationPresentation(
    modifier: Modifier = Modifier,
    name: () -> String,
    address: () -> String,
    imageUrl: () -> String,
    onMapClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
    ) {
        SimpleImage(
            modifier = Modifier
                .height(picHeight)
                .fillMaxWidth()
                .clip(AppTheme.shapes.small),
            url = imageUrl,
            backgroundColor = AppTheme.colors.surfaces
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)) {
                Text(
                    text = name(),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.title
                )
                Text(
                    text = address(),
                    color = AppTheme.colors.gray2,
                    style = AppTheme.typography.tertiaryText
                )
            }
            IconButton(
                onClick = onMapClick
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)
                ) {
                    MapIcon()
                    Text(
                        text = stringResource(id = R.string.map),
                        color = AppTheme.colors.primary,
                        style = AppTheme.typography.tertiaryText
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationPresentationPreview() {
    AppTheme {
        OrganizationPresentation(name = { "Кофемания" },
            address = { "Ленина 13 б" },
            imageUrl = { "" },
            onMapClick = {})
    }
}