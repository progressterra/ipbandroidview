package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SizeTableBottomSheet(
    modifier: Modifier = Modifier,
    url: String
) {
    Column(
        modifier = modifier
            .padding(
                top = AppTheme.dimensions.small,
                start = AppTheme.dimensions.small,
                end = AppTheme.dimensions.small
            )
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = stringResource(R.string.size_table),
            color = AppTheme.colors.black,
            style = AppTheme.typography.text
        )
    }
    WebViewCompose(url = url)
}