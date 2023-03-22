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
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val picHeight = 188.dp

@Composable
fun OrganizationPresentation(
    modifier: Modifier = Modifier,
    name: String,
    address: String,
    imageUrl: String,
    onMapClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .height(picHeight)
                .fillMaxWidth()
                .clip(IpbTheme.shapes.small),
            url = imageUrl,
            backgroundColor = IpbTheme.colors.surfaces
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = name, color = IpbTheme.colors.black, style = IpbTheme.typography.title
                )
                Text(
                    text = address,
                    color = IpbTheme.colors.gray2,
                    style = IpbTheme.typography.tertiary
                )
            }
            IconButton(
                onClick = onMapClick
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    MapIcon()
                    Text(
                        text = stringResource(id = R.string.map),
                        color = IpbTheme.colors.primary,
                        style = IpbTheme.typography.tertiary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun OrganizationPresentationPreview() {
    IpbTheme {
        OrganizationPresentation(
            name = "Кофемания",
            address = "Ленина б",
            imageUrl = "",
            onMapClick = {})
    }
}