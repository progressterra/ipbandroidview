package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.WebViewCompose
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun SizeTableComponent(
    modifier: Modifier = Modifier,
    url: String
) {
    Column(
        modifier = modifier
            .padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp
            )
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.size_table),
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.title,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
    WebViewCompose(url = url)
}

@Composable
@Preview
private fun SizeTableComponentPreview() {
    IpbTheme {
        SizeTableComponent(url = "https://www.google.com")
    }
}