package com.progressterra.ipbandroidview.features.voice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.PulsingDot


@Composable
fun Voice(
    modifier: Modifier = Modifier,
    state: VoiceState,
    onStartPlay: () -> Unit,
    onPausePlay: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onRemove: () -> Unit,
    enabled: Boolean
) {
    when (state) {
        is VoiceState.Recorder -> Box(modifier = modifier) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .height(TextFieldDefaults.MinHeight)
                    .fillMaxWidth()
                    .background(IpbTheme.colors.background.asBrush())
                    .padding(vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (state.ongoing) {
                    IconButton(onClick = onRemove, enabled = enabled) {
                        BrushedIcon(
                            resId = R.drawable.ic_trash,
                            tint = if (enabled) IpbTheme.colors.error.asBrush() else IpbTheme.colors.iconTertiary.asBrush()
                        )
                    }
                } else {
                    BrushedText(
                        modifier = Modifier.padding(start = 12.dp),
                        text = stringResource(id = R.string.voice_message),
                        style = IpbTheme.typography.body2,
                        tint = if (enabled) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textTertiary.asBrush()
                    )
                }
                IconButton(
                    onClick = if (state.ongoing) onStopRecording else onStartRecording,
                    enabled = enabled
                ) {
                    BrushedIcon(
                        resId = R.drawable.ic_mic,
                        tint = if (enabled) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconTertiary.asBrush()
                    )
                }
            }
            if (state.ongoing) PulsingDot(modifier = Modifier.align(Alignment.CenterEnd))
        }

        is VoiceState.Player -> Box(modifier = modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(TextFieldDefaults.MinHeight)
                    .background(IpbTheme.colors.background.asBrush())
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                IconButton(
                    onClick = onRemove,
                    enabled = enabled
                ) {
                    BrushedIcon(
                        resId = R.drawable.ic_trash,
                        tint = if (enabled) IpbTheme.colors.error.asBrush() else IpbTheme.colors.iconTertiary.asBrush()
                    )
                }
                LinearProgressIndicator(
                    modifier = Modifier.weight(1f),
                    color = IpbTheme.colors.primary.asColor(),
                    backgroundColor = IpbTheme.colors.surface.asColor(),
                    progress = state.progress
                )
                IconButton(onClick = if (state.ongoing) onPausePlay else onStartPlay) {
                    BrushedIcon(
                        resId = if (state.ongoing) R.drawable.ic_pause else R.drawable.ic_play,
                        tint = IpbTheme.colors.primary.asBrush()
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun VoicePreviewIdle() {
    IpbTheme {
        Voice(
            state = VoiceState.Recorder(false),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {},
            enabled = true
        )
    }
}

@Preview
@Composable
private fun VoicePreviewRecord() {
    IpbTheme {
        Voice(
            state = VoiceState.Recorder(true),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {},
            enabled = true
        )
    }
}


@Preview
@Composable
private fun VoicePreviewPlay() {
    IpbTheme {
        Voice(
            state = VoiceState.Player(true, 0.5f),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {},
            enabled = true
        )
    }
}

@Preview
@Composable
private fun VoicePreviewPause() {
    IpbTheme {
        Voice(
            state = VoiceState.Player(false, 0.3f),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {},
            enabled = true
        )
    }
}