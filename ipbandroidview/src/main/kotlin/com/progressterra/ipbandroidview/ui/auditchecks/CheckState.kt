package com.progressterra.ipbandroidview.ui.auditchecks

import android.os.Parcelable
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckState(
    val done: Boolean,
    val yesNo: YesNo
) : Parcelable