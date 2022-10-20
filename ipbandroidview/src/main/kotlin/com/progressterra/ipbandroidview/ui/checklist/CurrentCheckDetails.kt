package com.progressterra.ipbandroidview.ui.checklist

import android.os.Parcelable
import com.progressterra.ipbandroidview.core.Photo
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentCheckDetails(
    val attachedVoicePointer: String?,
    val attachedPhotosPointers: List<Photo>
) : Parcelable
