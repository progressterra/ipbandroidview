package com.progressterra.ipbandroidview.ui.auditchecks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Check(
    val state: CheckState,
    val id: String,
    val category: String,
    val name: String,
    val comment: String
) : Parcelable