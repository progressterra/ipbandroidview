package com.progressterra.ipbandroidview.features.documentphoto

import android.os.Parcelable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.entities.IsEmpty
import kotlinx.parcelize.Parcelize


@Parcelize
data class DocumentPhotoState(
    val items: List<MultisizedImage> = emptyList(),
    val enabled: Boolean = true
) : Parcelable, IsEmpty {

    override fun isEmpty(): Boolean = this == DocumentPhotoState()
}