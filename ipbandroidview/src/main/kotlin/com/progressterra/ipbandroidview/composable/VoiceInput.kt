package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
    onRemove: () -> Unit
) {
    when (state) {
        is VoiceState.Recorder ->
            Box(modifier = modifier) {
                Box(modifier = Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                            .background(AppTheme.colors.background)
                            .padding(horizontal = 12.dp, vertical = 14.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (state.ongoing)
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = onRemove
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_trash),
                                    contentDescription = stringResource(
                                        id = R.string.trash
                                    ),
                                    tint = AppTheme.colors.error
                                )
                            }
                        else
                            Text(
                                text = stringResource(id = R.string.voice_message),
                                style = AppTheme.typography.text,
                                color = AppTheme.colors.gray1
                            )
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = if (state.ongoing) onStopRecording else onStartRecording
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_mic),
                                contentDescription = stringResource(
                                    id = R.string.mic
                                ),
                                tint = AppTheme.colors.primary
                            )

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
                        .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                        .background(AppTheme.colors.background)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = onRemove
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trash),
                            contentDescription = stringResource(
                                id = R.string.trash
                            ),
                            tint = AppTheme.colors.error
                        )
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
                        Icon(
                            painter = painterResource(id = if (state.ongoing) R.drawable.ic_pause else R.drawable.ic_play),
                            contentDescription = stringResource(
                                id = R.string.pause_stop
                            ),
                            tint = AppTheme.colors.primary
                        )
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
            onRemove = {})
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
            onRemove = {})
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
            onRemove = {})
    }
}