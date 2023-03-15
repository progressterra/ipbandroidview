package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun AuditTitle(
    modifier: Modifier = Modifier,
    name: String,
    checkCounter: Int
) {
    Column(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = name,
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.title
        )
        Text(
            text = "${stringResource(id = R.string.questions)}: $checkCounter",
            color = IpbTheme.colors.gray2,
            style = IpbTheme.typography.tertiaryText
        )
    }
}

@Preview
@Composable
private fun AuditTitlePreview() {
    IpbTheme {
        AuditTitle(
            name = "Some cool audit", checkCounter = 999
        )
    }
}