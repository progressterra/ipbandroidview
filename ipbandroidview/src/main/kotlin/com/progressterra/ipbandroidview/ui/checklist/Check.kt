package com.progressterra.ipbandroidview.ui.checklist

import android.os.Parcelable
import com.progressterra.ipbandroidview.components.yesno.YesNo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Check(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val categoryNumber: Int,
    val ordinal: Int,
    val yesNo: YesNo,
    val comment: String
) : Parcelable