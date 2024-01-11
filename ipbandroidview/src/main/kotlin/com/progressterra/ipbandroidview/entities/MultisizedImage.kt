package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MultisizedImage(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    val url: String
) : Parcelable, AttachedMedia<MultisizedImage> {

    override fun markToRemove(): MultisizedImage = this.copy(toRemove = true)
}