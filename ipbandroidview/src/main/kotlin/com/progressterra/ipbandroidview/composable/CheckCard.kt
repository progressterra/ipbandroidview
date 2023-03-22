package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun CheckCard(
    modifier: Modifier = Modifier,
    state: Check,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(if (state.yesNo == true) IpbTheme.colors.success else if (state.yesNo == false) IpbTheme.colors.failed else IpbTheme.colors.surfaces)
            .niceClickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.weight(1f, false),
            text = state.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.primary
        )
        Spacer(modifier = Modifier.size(16.dp))
        ForwardIcon()
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckCardPreviewOngoing() {
    IpbTheme {
        CheckCard(
            state = Check(
                id = "",
                name = "Some check",
                description = "",
                category = "",
                categoryNumber = 0,
                ordinal = 0,
                yesNo = null,
                comment = ""
            ),
            onClick = {}
        )
    }
}