package com.progressterra.ipbandroidview.features.documentphoto

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class DocumentPhotoState(
    val items: List<MultisizedImage> = emptyList(),
    val docName: String = "",
    val required: Boolean = false,
    val enabled: Boolean = true
) : Parcelable, IsEmpty {
    override fun isEmpty(): Boolean = this == DocumentPhotoState()

    companion object
}