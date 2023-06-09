package com.progressterra.ipbandroidview.features.documentphoto

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class DocumentPhotoState(
    val items: List<MultisizedImage> = emptyList(),
    val docName: String = "",
    val enabled: Boolean = true
) : Parcelable {

    fun uEnabled(newEnabled: Boolean) = copy(enabled = newEnabled)

    fun add(item: MultisizedImage) = copy(items = items + item)

    fun remove(item: MultisizedImage) = copy(items = items - item)
}