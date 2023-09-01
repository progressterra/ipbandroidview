package com.progressterra.ipbandroidview.features.currentcitizenship

import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerState


data class CurrentCitizenshipState(
    val citizenship: Citizenship? = null,
    val dialog: DialogPickerState = DialogPickerState()
)