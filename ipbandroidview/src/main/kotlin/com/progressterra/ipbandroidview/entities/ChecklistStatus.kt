package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ChecklistStatus : Parcelable {
    READ_ONLY,
    CAN_BE_STARTED,
    ONGOING;

    fun isCanBeStarted(): Boolean = this == CAN_BE_STARTED

    fun isOngoing(): Boolean = this == ONGOING
}