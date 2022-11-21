package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.AttachedMedia
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckPicture(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    val thumbnail: String,
    val fullSize: String
) : Parcelable, AttachedMedia<CheckPicture> {

    override fun markToRemove(): CheckPicture = this.copy(toRemove = true)
}