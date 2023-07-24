package com.progressterra.ipbandroidview.features.documentphoto

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
@optics
data class DocumentPhotoState(
    val items: List<MultisizedImage> = emptyList(),
    val docName: String = "",
    val enabled: Boolean = true
) : Parcelable, IsEmpty {
    override fun isEmpty(): Boolean =
        items == emptyList<MultisizedImage>() && docName == "" && enabled

    fun uEnabled(newEnabled: Boolean) = copy(enabled = newEnabled)

    fun add(item: MultisizedImage) = copy(items = items + item)

    fun remove(item: MultisizedImage) = copy(items = items - item)

    companion object
}