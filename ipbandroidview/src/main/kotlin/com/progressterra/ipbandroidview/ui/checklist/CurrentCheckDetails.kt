package com.progressterra.ipbandroidview.ui.checklist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentCheckDetails(
    val attachedVoicePointer: String?,
    val attachedPhotosPointers: List<String>
) : Parcelable
