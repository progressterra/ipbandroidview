package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsSize(
    val available: Boolean,
    val primary: String,
    val secondary: String?
) : Parcelable