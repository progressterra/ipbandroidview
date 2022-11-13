package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationCard(
    modifier: Modifier = Modifier,
    address: () -> String,
    description: () -> String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .niceClickable(onClick = onClick)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f, false)) {
            Text(
                text = address(),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.tiny))
            Text(
                text = description(),
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
            address = { "пл Дружбы народов, 45" },
            description = { "«Кофемания»" },
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun OrganizationPreviewLarge() {
    AppTheme {
        OrganizationCard(
            modifier = Modifier.fillMaxWidth(),
            address = { "пл Дружбы народов, 45" },
            description = { "«Кофемания»" },
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun OrganizationPreviewEmpty() {
    AppTheme {
        OrganizationCard(
            modifier = Modifier.fillMaxWidth(),
            address = { "пл Дружбы народов, 45" },
            description = { "«Кофемания»" }, onClick = {}
        )
    }
}