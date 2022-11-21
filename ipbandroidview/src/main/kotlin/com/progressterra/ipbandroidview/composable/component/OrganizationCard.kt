package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.element.ForwardIcon
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.organizations.Organization

@Composable
fun OrganizationCard(
    modifier: Modifier = Modifier,
    state: () -> Organization,
    openOrganization: (Organization) -> Unit
) {
    Row(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .niceClickable(onClick = { openOrganization(state()) })
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f, false)) {
            Text(
                text = state().address,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.tiniest))
            Text(
                text = state().name,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.secondaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        ForwardIcon()
    }
}

@Preview
@Composable
private fun OrganizationPreviewSmall() {
    AppTheme {
        OrganizationCard(
            state = {
                Organization(
                    address = "123 132 123",
                    id = "",
                    warnings = 0,
                    name = "Somme coool name",
                    imageUrl = "",
                    latitude = 0.0,
                    longitude = 0.0
                )
            },
            openOrganization = {}
        )
    }
}