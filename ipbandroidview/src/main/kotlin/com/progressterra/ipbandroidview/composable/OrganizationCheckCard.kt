package com.progressterra.ipbandroidview.composable

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
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun OrganizationCheckCard(
    modifier: Modifier = Modifier,
    name: String,
    lastTime: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .niceClickable { onClick() }
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = name, color = IpbTheme.colors.black, style = IpbTheme.typography.text)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = lastTime,
                color = IpbTheme.colors.gray2,
                style = IpbTheme.typography.secondaryText
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
    IpbTheme {
        OrganizationCheckCard(name = "Name", lastTime = "Last time", onClick = {})
    }
}

@Preview
@Composable
private fun OrganizationCheckCardPreviewWarning() {
    IpbTheme {
        OrganizationCheckCard(name = "Name", lastTime = "Last time", onClick = {})
    }
}