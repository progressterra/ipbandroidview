package com.progressterra.ipbandroidview.ui.checklist

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.yesno.YesNo
import com.progressterra.ipbandroidview.model.component.Id
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Check(
    override val id: String,
    val name: String,
    val description: String,
    val category: String,
    val categoryNumber: Int,
    val ordinal: Int,
    val yesNo: YesNo,
    val comment: String
) : Parcelable, Id