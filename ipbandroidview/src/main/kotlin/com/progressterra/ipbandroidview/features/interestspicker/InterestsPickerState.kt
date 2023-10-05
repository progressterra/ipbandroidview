package com.progressterra.ipbandroidview.features.interestspicker

import com.progressterra.ipbandroidview.entities.Interest

data class InterestsPickerState(
    val allInterests: List<Interest> = emptyList(),
    val changedInterests: List<Interest> = emptyList(),
)