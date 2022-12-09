package com.progressterra.ipbandroidview.model.store

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoodsSize(
    val available: Boolean = false,
    val primary: String = "",
    val secondary: String? = null
) : Parcelable