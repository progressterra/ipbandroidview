package com.progressterra.ipbandroidview.composable

import android.os.Parcelable
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
import kotlinx.parcelize.Parcelize

sealed class VoiceState : Parcelable {

    @Parcelize
    object IDLE : VoiceState()

    @Parcelize
    object RECORD : VoiceState()

    @Parcelize
    class PLAY(val listened: Float) : VoiceState()

    @Parcelize
    class PAUSE(val listened: Float) : VoiceState()
}

@Composable
fun VoiceInput(
    modifier: Modifier = Modifier,
    voiceState: VoiceState,
    onStartPausePlay: () -> Unit,
    onStartStopRecording: () -> Unit,
    onRemove: () -> Unit,
    enabled: Boolean = true
) {
    Box(modifier = modifier) {
        Box(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                    .background(AppTheme.colors.background)
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                when (voiceState) {
                    is VoiceState.IDLE -> {
                        Text(
                            text = stringResource(id = R.string.voice_message),
                            style = AppTheme.typography.text,
                            color = AppTheme.colors.gray1
                        )
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onStartStopRecording, enabled = enabled
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
                    is VoiceState.PAUSE -> {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onRemove,
                            enabled = enabled
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
                            progress = voiceState.listened
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onStartPausePlay,
                            enabled = enabled
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_play),
                                contentDescription = stringResource(
                                    id = R.string.mic
                                ),
                                tint = AppTheme.colors.primary
                            )
                        }
                    }
                    is VoiceState.PLAY -> {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onRemove,
                            enabled = enabled
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
                            progress = voiceState.listened
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onStartPausePlay,
                            enabled = enabled
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pause),
                                contentDescription = stringResource(
                                    id = R.string.pause
                                ),
                                tint = AppTheme.colors.primary
                            )
                        }

                    }
                    is VoiceState.RECORD -> {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onRemove,
                            enabled = enabled
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = stringResource(
                                    id = R.string.trash
                                ),
                                tint = AppTheme.colors.error
                            )
                        }
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onStartPausePlay,
                            enabled = enabled
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
            }
        }
        if (voiceState == VoiceState.RECORD)
            PulsingDot(modifier = Modifier.align(Alignment.CenterEnd), size = 64.dp)
    }
}

@Preview
@Composable
fun VoiceInputPreviewIdle() {
    AppTheme {
        VoiceInput(
            voiceState = VoiceState.IDLE,
            onStartPausePlay = {},
            onStartStopRecording = {},
            onRemove = {})
    }
}

@Preview
@Composable
fun VoiceInputPreviewRecord() {
    AppTheme {
        VoiceInput(
            voiceState = VoiceState.RECORD,
            onStartPausePlay = {},
            onStartStopRecording = {},
            onRemove = {})
    }
}


@Preview
@Composable
fun VoiceInputPreviewPlay() {
    AppTheme {
        VoiceInput(
            voiceState = VoiceState.PLAY(0.4f),
            onStartPausePlay = {},
            onStartStopRecording = {},
            onRemove = {})
    }
}

@Preview
@Composable
fun VoiceInputPreviewPause() {
    AppTheme {
        VoiceInput(
            voiceState = VoiceState.PAUSE(0.9f),
            onStartPausePlay = {},
            onStartStopRecording = {},
            onRemove = {})
    }
}