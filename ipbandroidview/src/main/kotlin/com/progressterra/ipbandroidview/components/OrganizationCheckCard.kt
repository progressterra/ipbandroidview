package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrganizationCheckCard(
    modifier: Modifier = Modifier,
    name: String = "",
    lastTime: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
            .niceClickable(onClick = onClick)
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
            ForwardIcon()
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
        OrganizationCheckCard(name = "Name", lastTime = "Last time", onClick = {})
    }
}