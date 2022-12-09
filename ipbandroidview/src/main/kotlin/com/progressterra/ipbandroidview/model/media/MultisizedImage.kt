package com.progressterra.ipbandroidview.model.media

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.AttachedMedia
import kotlinx.parcelize.Parcelize

@Parcelize
data class MultisizedImage(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    val thumbnail: String,
    val fullSize: String
) : Parcelable, AttachedMedia<MultisizedImage> {

    override fun markToRemove(): MultisizedImage = this.copy(toRemove = true)
}