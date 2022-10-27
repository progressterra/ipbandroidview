package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

sealed class VoiceState(
    val ongoing: Boolean
) {

    class Recorder(ongoing: Boolean) : VoiceState(ongoing)

    class Player(ongoing: Boolean, val progress: Float) : VoiceState(ongoing)
}


@Composable
fun VoiceInput(
    modifier: Modifier = Modifier,
    state: VoiceState,
    onStartPlay: () -> Unit,
    onPausePlay: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onRemove: () -> Unit,
    enabled: Boolean = true
) {
    when (state) {
        is VoiceState.Recorder ->
            Box(modifier = modifier) {
                Box(modifier = Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(AppTheme.roundings.smallRounding))
                            .height(TextFieldDefaults.MinHeight)
                            .fillMaxWidth()
                            .background(AppTheme.colors.background)
                            .padding(horizontal = 12.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (state.ongoing)
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = onRemove,
                                enabled = enabled
                            ) {
                                TrashIcon(enabled = enabled)
                            }
                        else
                            Text(
                                text = stringResource(id = R.string.voice_message),
                                style = AppTheme.typography.text,
                                color = if (enabled) AppTheme.colors.gray1 else AppTheme.colors.gray2
                            )
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = if (state.ongoing) onStopRecording else onStartRecording,
                            enabled = enabled
                        ) {
                            MicIcon(enabled = enabled)
                        }
                    }

                }
                if (state.ongoing)
                    PulsingDot(modifier = Modifier.align(Alignment.CenterEnd), size = 64.dp)
            }
        is VoiceState.Player ->
            Box(modifier = modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(AppTheme.roundings.smallRounding))
                        .background(AppTheme.colors.background)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = onRemove,
                        enabled = enabled
                    ) {
                        TrashIcon(enabled = enabled)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    ThemedLinearProgressIndicator(
                        modifier = Modifier.weight(1f),
                        progress = state.progress
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = if (state.ongoing) onPausePlay else onStartPlay
                    ) {
                        PlayPauseIcon(ongoing = state.ongoing)
                    }
                }

            }
    }
}

@Preview
@Composable
private fun VoiceInputPreviewIdle() {
    AppTheme {
        VoiceInput(
            state = VoiceState.Recorder(false),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {}
        )
    }
}

@Preview
@Composable
private fun VoiceInputPreviewRecord() {
    AppTheme {
        VoiceInput(
            state = VoiceState.Recorder(true),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {}
        )
    }
}


@Preview
@Composable
private fun VoiceInputPreviewPlay() {
    AppTheme {
        VoiceInput(
            state = VoiceState.Player(true, 0.5f),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {}
        )
    }
}

@Preview
@Composable
private fun VoiceInputPreviewPause() {
    AppTheme {
        VoiceInput(
            state = VoiceState.Player(false, 0.3f),
            onStartPlay = {},
            onPausePlay = {},
            onStartRecording = {},
            onStopRecording = {},
            onRemove = {}
        )
    }
}