package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    padding: Dp = 12.dp,
    verticalGap: Dp = 2.dp,
    horizontalGap: Dp = 16.dp,
    address: String = "",
    description: String = "",
    availableChecks: Int = 0,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .background(AppTheme.colors.surfaces)
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f, false)) {
            Text(
                text = address,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.size(verticalGap))
            Text(
                text = description,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.secondaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(horizontalGap))
            if (availableChecks > 0) AvailableChecks(count = availableChecks)
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_forward),
                contentDescription = stringResource(
                    id = R.string.forward
                ),
                tint = AppTheme.colors.gray2
            )
        }
    }
}

@Preview
@Composable
private fun OrganizationPreviewSmall() {
    AppTheme {
        OrganizationCard(
            address = "пл Дружбы народов, 45",
            description = "«Кофемания»",
            availableChecks = 1,
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
            address = "пл Дружбы народов, 45",
            description = "«Кофемания»",
            availableChecks = 1, onClick = {}
        )
    }
}

@Preview
@Composable
private fun OrganizationPreviewEmpty() {
    AppTheme {
        OrganizationCard(
            modifier = Modifier.fillMaxWidth(),
            address = "пл Дружбы народов, 45",
            description = "«Кофемания»", onClick = {}
        )
    }
}