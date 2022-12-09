package com.progressterra.ipbandroidview.model.store

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderResult(
    val success: Boolean = false,
    val additionalInfo: String = ""
) : Parcelable
