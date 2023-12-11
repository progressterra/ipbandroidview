package com.progressterra.ipbandroidview.shared.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

@Composable
fun SimpleDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    text: String,
    action: String,
    onAction: () -> Unit,
    onDismiss: () -> Unit
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                BrushedText(
                    text = text,
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = ButtonState(id = "dialog"),
                    title = action,
                    useComponent = object : UseButton {
                        override fun handle(event: ButtonEvent) {
                            onAction()
                            onDismiss()
                        }
                    }
                )
            }
        }
    }
}