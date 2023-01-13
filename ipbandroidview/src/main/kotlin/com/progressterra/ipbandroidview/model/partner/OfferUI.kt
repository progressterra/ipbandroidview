package com.progressterra.ipbandroidview.model.partner

import android.os.Parcelable
import com.progressterra.ipbandroidview.model.Id
import kotlinx.parcelize.Parcelize

@Parcelize
data class OfferUI(
    override val id: String,
    val headDescription: String,
    val fullDescription: String,
    val imageUrl: String
) : Id, Parcelable
