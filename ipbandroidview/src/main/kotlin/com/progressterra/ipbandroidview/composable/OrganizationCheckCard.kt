package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationCheckCard(
    modifier: Modifier = Modifier,
    name: String = "",
    lastTime: String = "",
    warning: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .background(AppTheme.colors.surfaces)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = name, color = AppTheme.colors.black, style = AppTheme.typography.text)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = lastTime,
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.secondaryText
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (warning) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(AppTheme.colors.error)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "!",
                        color = AppTheme.colors.surfaces,
                        style = AppTheme.typography.tertiaryText
                    )
                }
            }
            Icon(
                modifier = Modifier.size(24.dp),
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
private fun OrganizationCheckCardPreview() {
    AppTheme {
        OrganizationCheckCard(name = "Name", lastTime = "Last time", onClick = {})
    }
}

@Preview
@Composable
private fun OrganizationCheckCardPreviewWarning() {
    AppTheme {
        OrganizationCheckCard(name = "Name", lastTime = "Last time", warning = true, onClick = {})
    }
}