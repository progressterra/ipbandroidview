package com.progressterra.ipbandroidview.model.checklist

enum class ChecklistStatus {
    READ_ONLY,
    CAN_BE_STARTED,
    ONGOING;

    fun isCanBeStarted(): Boolean = this == CAN_BE_STARTED

    fun isReadOnly(): Boolean = this == READ_ONLY

    fun isOngoing(): Boolean = this == ONGOING
}