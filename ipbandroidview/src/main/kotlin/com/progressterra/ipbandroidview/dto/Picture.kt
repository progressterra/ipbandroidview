package com.progressterra.ipbandroidview.dto

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.AttachedMedia
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    val thumbnail: String,
    val fullSize: String
) : Parcelable, AttachedMedia<Picture> {

    override fun markToRemove(): Picture = this.copy(toRemove = true)
}