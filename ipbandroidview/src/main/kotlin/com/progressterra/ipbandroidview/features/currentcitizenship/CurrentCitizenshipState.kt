package com.progressterra.ipbandroidview.features.currentcitizenship

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerState

@Immutable
@optics data class CurrentCitizenshipState(
    val citizenship: Citizenship? = null,
    val dialog: DialogPickerState = DialogPickerState()
) { companion object }