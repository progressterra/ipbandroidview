package com.progressterra.ipbandroidview.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Picture(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    open val thumbnail: String,
    open val fullSize: String
) : Parcelable, AttachedMedia<Picture> {

    data class Local(
        override val id: String,
        override val toRemove: Boolean,
        override val thumbnail: String,
        override val fullSize: String,
    ) : Picture(
        id = id,
        local = true,
        toRemove = toRemove,
        fullSize = fullSize,
        thumbnail = thumbnail
    ) {

        override fun markToRemove(): Picture = this.copy(toRemove = true)
    }

    data class Remote(
        override val id: String,
        override val toRemove: Boolean,
        override val thumbnail: String,
        override val fullSize: String,
    ) : Picture(
        id = id,
        local = false,
        toRemove = toRemove,
        fullSize = fullSize,
        thumbnail = thumbnail
    ) {

        override fun markToRemove(): Picture = this.copy(toRemove = true)
    }
}