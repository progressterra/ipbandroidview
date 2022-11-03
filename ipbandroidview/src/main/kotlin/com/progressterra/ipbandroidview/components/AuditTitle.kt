package com.progressterra.ipbandroidview.components

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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun AuditTitle(
    modifier: Modifier = Modifier,
    name: String = "",
    checkCounter: Int = 0
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = name,
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        Text(
            text = "${stringResource(id = R.string.questions)}: $checkCounter",
            color = AppTheme.colors.gray2,
            style = AppTheme.typography.tertiaryText
        )
    }
}

@Preview
@Composable
private fun AuditTitlePreview() {
    AppTheme {
        AuditTitle(
            name = "Some name",
            checkCounter = 15
        )
    }
}