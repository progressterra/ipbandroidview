package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
sealed class VoiceState(
    val ongoing: Boolean
) {

    class Recorder(ongoing: Boolean) : VoiceState(ongoing)

    class Player(ongoing: Boolean, val progress: Float) : VoiceState(ongoing)
}

private val verticalPadding = 14.dp

@Composable
fun VoiceInput(
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
                        .clip(IpbTheme.shapes.small)
                        .height(TextFieldDefaults.MinHeight)
                        .fillMaxWidth()
                        .background(IpbTheme.colors.background)
                        .padding(vertical = verticalPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (state.ongoing) IconButton(onClick = onRemove, enabled = enabled) {
                        TrashIcon(enabled = enabled)
                    }
                    else Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = stringResource(id = R.string.voice_message),
                        style = IpbTheme.typography.text,
                        color = if (enabled) IpbTheme.colors.gray1 else IpbTheme.colors.gray2
                    )
                    IconButton(
                        onClick = if (state.ongoing) onStopRecording else onStartRecording,
                        enabled = enabled
                    ) {
                        MicIcon(enabled = enabled)
                    }
            }
            if (state.ongoing) PulsingDot(modifier = Modifier.align(Alignment.CenterEnd))
        }
        is VoiceState.Player -> Box(modifier = modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .clip(IpbTheme.shapes.small)
                    .height(TextFieldDefaults.MinHeight)
                    .background(IpbTheme.colors.background)
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(
                    onClick = onRemove,
                    enabled = enabled
                ) { TrashIcon(enabled = enabled) }
                ThemedLinearProgressIndicator(
                    modifier = Modifier.weight(1f),
                    progress = state.progress
                )
                IconButton(onClick = if (state.ongoing) onPausePlay else onStartPlay) {
                    PlayPauseIcon(ongoing = state.ongoing)
                }
            }

        }
    }
}

@Preview
@Composable
private fun VoiceInputPreviewIdle() {
    IpbTheme {
        VoiceInput(
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
private fun VoiceInputPreviewRecord() {
    IpbTheme {
        VoiceInput(
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
private fun VoiceInputPreviewPlay() {
    IpbTheme {
        VoiceInput(
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
private fun VoiceInputPreviewPause() {
    IpbTheme {
        VoiceInput(
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