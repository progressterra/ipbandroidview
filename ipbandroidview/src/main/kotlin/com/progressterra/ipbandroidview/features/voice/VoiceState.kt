package com.progressterra.ipbandroidview.features.voice

import androidx.compose.runtime.Immutable

@Immutable
sealed class VoiceState(
    val ongoing: Boolean
) {

    class Recorder(ongoing: Boolean) : VoiceState(ongoing)

    class Player(ongoing: Boolean, val progress: Float) : VoiceState(ongoing)
}