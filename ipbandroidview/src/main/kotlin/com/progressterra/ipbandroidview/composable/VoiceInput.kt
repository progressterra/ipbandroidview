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

sealed class VoiceState {
    object IDLE : VoiceState()
    object RECORD : VoiceState()
    class PLAY(val listened: Float) : VoiceState()
    class PAUSE(val listened: Float) : VoiceState()
}

@Composable
fun VoiceInput(
    modifier: Modifier = Modifier,
    voiceState: VoiceState,
    onStartStop: () -> Unit,
    onRecord: () -> Unit,
    onRemove: () -> Unit
) {
    Box(modifier = modifier) {
        Box(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimensions.tinyRounding))
                    .background(AppTheme.colors.background)
                    .padding(12.dp),
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
                        IconButton(modifier = Modifier.size(24.dp), onClick = onRecord) {
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
                        IconButton(modifier = Modifier.size(24.dp), onClick = onRemove) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = stringResource(
                                    id = R.string.trash
                                ),
                                tint = AppTheme.colors.error
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(AppTheme.dimensions.lineRounding))
                                .background(AppTheme.colors.surfaces),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(voiceState.listened)
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(AppTheme.dimensions.lineRounding))
                                    .background(AppTheme.colors.primary)
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        IconButton(modifier = Modifier.size(24.dp), onClick = onStartStop) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_mic),
                                contentDescription = stringResource(
                                    id = R.string.mic
                                ),
                                tint = AppTheme.colors.primary
                            )
                        }
                    }
                    is VoiceState.PLAY -> {
                        IconButton(modifier = Modifier.size(24.dp), onClick = onRemove) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = stringResource(
                                    id = R.string.trash
                                ),
                                tint = AppTheme.colors.error
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(AppTheme.dimensions.lineRounding))
                                .background(AppTheme.colors.surfaces),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(voiceState.listened)
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(AppTheme.dimensions.lineRounding))
                                    .background(AppTheme.colors.primary)
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        IconButton(modifier = Modifier.size(24.dp), onClick = onStartStop) {
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
                        IconButton(modifier = Modifier.size(24.dp), onClick = onRemove) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = stringResource(
                                    id = R.string.trash
                                ),
                                tint = AppTheme.colors.error
                            )
                        }
                        IconButton(modifier = Modifier.size(24.dp), onClick = onStartStop) {
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
        VoiceInput(voiceState = VoiceState.IDLE, onStartStop = {}, onRecord = {}, onRemove = {})
    }
}

@Preview
@Composable
fun VoiceInputPreviewRecord() {
    AppTheme {
        VoiceInput(voiceState = VoiceState.RECORD, onStartStop = {}, onRecord = {}, onRemove = {})
    }
}


@Preview
@Composable
fun VoiceInputPreviewPlay() {
    AppTheme {
        VoiceInput(
            voiceState = VoiceState.PLAY(0.4f),
            onStartStop = {},
            onRecord = {},
            onRemove = {})
    }
}

@Preview
@Composable
fun VoiceInputPreviewPause() {
    AppTheme {
        VoiceInput(
            voiceState = VoiceState.PAUSE(0.9f),
            onStartStop = {},
            onRecord = {},
            onRemove = {})
    }
}