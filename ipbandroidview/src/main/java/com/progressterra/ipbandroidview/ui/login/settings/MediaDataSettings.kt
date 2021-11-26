package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MediaDataSettings(
    val idEntity: String,
    val authority: String
) : Parcelable