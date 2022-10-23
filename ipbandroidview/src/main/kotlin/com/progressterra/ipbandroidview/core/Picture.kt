package com.progressterra.ipbandroidview.core

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Picture(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    open val fullSize: String
) : Parcelable, AttachedMedia<Picture> {

    data class Local(
        override val id: String,
        override val toRemove: Boolean,
        override val fullSize: String,
        val thumbnail: Bitmap
    ) : Picture(id = id, local = true, toRemove = toRemove, fullSize = fullSize) {

        override fun markToRemove(): Picture = this.copy(toRemove = true)
    }

    data class Remote(
        override val id: String,
        override val toRemove: Boolean,
        override val fullSize: String,
        val thumbnail: String
    ) : Picture(id = id, local = false, toRemove = toRemove, fullSize = fullSize) {

        override fun markToRemove(): Picture = this.copy(toRemove = true)
    }
}