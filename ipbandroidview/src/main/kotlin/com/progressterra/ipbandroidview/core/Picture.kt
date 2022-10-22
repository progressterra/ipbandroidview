package com.progressterra.ipbandroidview.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(
    override val id: String,
    override val local: Boolean,
    override val toRemove: Boolean,
    val thumbnail: String,
    val fullSize: String
) : Parcelable, AttachedMedia<Picture>
