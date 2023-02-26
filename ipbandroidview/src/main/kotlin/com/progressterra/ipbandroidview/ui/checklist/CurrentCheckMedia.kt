package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.ext.formPatch
import com.progressterra.ipbandroidview.ext.markLastToRemove
import com.progressterra.ipbandroidview.ext.markToRemove
import com.progressterra.ipbandroidview.model.MultisizedImage
import com.progressterra.ipbandroidview.model.Voice

@Immutable
data class CurrentCheckMedia(
    val voices: List<Voice> = emptyList(),
    val pictures: List<MultisizedImage> = emptyList()
) {
    fun createPatched() = copy(
        voices = voices.formPatch(), pictures = pictures.formPatch()
    )

    fun removeImage(image: MultisizedImage): CurrentCheckMedia =
        copy(pictures = pictures.markToRemove(image))

    fun addImage(image: MultisizedImage): CurrentCheckMedia =
        copy(pictures = pictures.plus(image))

    fun removeRecord(): CurrentCheckMedia = copy(voices = voices.markLastToRemove())

    fun addVoice(voice: Voice): CurrentCheckMedia =
        copy(voices = voices.plus(voice))
}