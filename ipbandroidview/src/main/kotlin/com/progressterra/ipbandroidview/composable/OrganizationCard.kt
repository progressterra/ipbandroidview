package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.Organization
import com.progressterra.ipbandroidview.theme.IpbTheme

private val imageHeight = 60.dp
private val imageWidth = 120.dp

@Composable
fun OrganizationCard(
    modifier: Modifier = Modifier,
    state: Organization,
    openOrganization: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .niceClickable { openOrganization() }
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(width = imageWidth, height = imageHeight)
                .clip(IpbTheme.shapes.small),
            url = state.imageUrl,
            backgroundColor = IpbTheme.colors.surfaces
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = state.address,
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = state.name,
                color = IpbTheme.colors.gray2,
                style = IpbTheme.typography.secondaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AuditsIcon()
                Text(
                    text = state.audits,
                    color = IpbTheme.colors.gray2,
                    style = IpbTheme.typography.secondaryText
                )
                DocumentsIcon()
                Text(
                    text = state.documents,
                    color = IpbTheme.colors.gray2,
                    style = IpbTheme.typography.secondaryText
                )
            }
        }
        ForwardIcon()
    }
}

@Preview
@Composable
private fun OrganizationPreviewSmall() {
    IpbTheme {
        OrganizationCard(
            state = Organization(
                address = "123 132 123",
                id = "",
                name = "Somme coool name",
                imageUrl = "",
                latitude = 0.0,
                longitude = 0.0,
                audits = "30",
                documents = "155"
            ),
            openOrganization = {}
        )
    }
}