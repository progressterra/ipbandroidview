package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme

//TODO Colors to composable

@Composable
fun CheckCard(
    modifier: Modifier = Modifier,
    yesNo: () -> Boolean?,
    onClick: () -> Unit,
    name: () -> String
) {
    Row(modifier = modifier
        .clip(AppTheme.shapes.medium)
        .background(if (yesNo() == true) AppTheme.colors.success else if (yesNo() == false) AppTheme.colors.failed else AppTheme.colors.surfaces)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true)
        ) { onClick() }
        .padding(AppTheme.dimensions.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.weight(1f, false),
            text = name(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.black,
            style = AppTheme.typography.text
        )
        Spacer(modifier = Modifier.size(AppTheme.dimensions.large))
        ForwardIcon()
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckCardPreviewOngoing() {
    AppTheme {
        CheckCard(
            name = { "Наличие сопроводительных документов (ветеринарных справок \"Меркурий\", деклараций о соответствии), их соответствие маркировкам" },
            onClick = {},
            yesNo = { true }
        )
    }
}