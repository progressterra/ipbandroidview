package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.WebViewCompose

//TODO уточнить какие размеры должны быть у таблицы
@Composable
fun SizeTableComponent(
    modifier: Modifier = Modifier,
    url: String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrushedText(
            text = stringResource(R.string.size_table),
            tint = IpbTheme.colors.textPrimary1.asBrush(),
            style = IpbTheme.typography.title,
            textAlign = TextAlign.Center
        )
        WebViewCompose(url = url)
    }
}

@Composable
@Preview
private fun SizeTableComponentPreview() {
    IpbTheme {
        SizeTableComponent(url = "https://www.google.com")
    }
}