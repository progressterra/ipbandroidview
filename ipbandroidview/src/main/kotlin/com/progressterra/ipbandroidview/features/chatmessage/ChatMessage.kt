package com.progressterra.ipbandroidview.features.chatmessage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun ChatMessage(
    modifier: Modifier = Modifier,
    message: Message
) {
    val paddingValues = PaddingValues(
        start = if (message.user) 40.dp else 20.dp,
        end = if (message.user) 20.dp else 40.dp
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalArrangement = if (message.user) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(IpbTheme.colors.surface1.asBrush())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            BrushedText(
                text = message.content,
                tint = IpbTheme.colors.textPrimary1.asBrush(),
                style = IpbTheme.typography.body
            )
            BrushedText(
                text = message.date,
                tint = IpbTheme.colors.textTertiary1.asBrush(),
                style = IpbTheme.typography.footnoteRegular
            )
        }
    }
}