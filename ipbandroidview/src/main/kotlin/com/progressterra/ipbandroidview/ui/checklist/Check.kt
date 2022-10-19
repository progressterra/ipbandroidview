package com.progressterra.ipbandroidview.ui.checklist

import android.os.Parcelable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import kotlinx.parcelize.Parcelize

/**
 * @param attachedVoicePointer - pointer can be url or path, it depends
 */
@Parcelize
data class Check(
    val yesNo: YesNo,
    val id: String,
    val category: String,
    val name: String,
    val comment: String,
    val description: String,
    val attachedVoicePointer: String?,
    val attachedPhotosPointers: List<String>?
) : Parcelable