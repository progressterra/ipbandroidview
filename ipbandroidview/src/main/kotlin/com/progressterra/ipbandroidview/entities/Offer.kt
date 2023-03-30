package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Offer(
    override val id: String,
    val headDescription: String,
    val fullDescription: String,
    val imageUrl: String
) : Id, Parcelable
