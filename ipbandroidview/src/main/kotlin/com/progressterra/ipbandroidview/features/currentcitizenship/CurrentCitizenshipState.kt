package com.progressterra.ipbandroidview.features.currentcitizenship

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerState

@Immutable
data class CurrentCitizenshipState(
    val citizenship: Citizenship? = null,
    val dialog: DialogPickerState = DialogPickerState()
) {

    fun uOpen(newOpen: Boolean) = copy(dialog = dialog.uOpen(newOpen))

    fun uSelected(newSelected: Citizenship) = copy(dialog = dialog.uSelected(newSelected))

    fun uCitizenship(newCitizenship: Citizenship) = copy(citizenship = newCitizenship)
}