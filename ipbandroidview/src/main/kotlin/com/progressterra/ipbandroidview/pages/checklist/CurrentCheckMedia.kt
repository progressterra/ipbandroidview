package com.progressterra.ipbandroidview.pages.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.entities.Voice
import com.progressterra.ipbandroidview.shared.formPatch
import com.progressterra.ipbandroidview.shared.markLastToRemove
import com.progressterra.ipbandroidview.shared.markToRemove

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