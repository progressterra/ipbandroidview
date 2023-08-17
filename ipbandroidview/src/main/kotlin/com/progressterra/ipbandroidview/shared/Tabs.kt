package com.progressterra.ipbandroidview.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    currentIndex: Int,
    onTabClicked: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEachIndexed { index, text ->
            val selected = currentIndex == index
            val backgroundColor =
                if (selected) IpbTheme.colors.background else IpbTheme.colors.surface
            Box(modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor.asBrush())
                .niceClickable { onTabClicked(index) }
                .padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
                val textColor =
                    if (selected) IpbTheme.colors.textPressed else IpbTheme.colors.textSecondary
                val style =
                    if (selected) IpbTheme.typography.subHeadlineBold else IpbTheme.typography.subHeadlineRegular
                BrushedText(
                    text = text,
                    tint = textColor.asBrush(),
                    style = style,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}