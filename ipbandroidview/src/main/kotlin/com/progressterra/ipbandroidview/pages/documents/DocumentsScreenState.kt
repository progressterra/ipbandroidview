package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

@Immutable
data class DocumentsScreenState(
    val documents: DocumentsState = DocumentsState(),
    val citizenship: CurrentCitizenshipState = CurrentCitizenshipState(),
    val screen: ScreenState = ScreenState.LOADING
) {

    fun uCurrent(newCurrent: CurrentCitizenshipState) = copy(citizenship = newCurrent)


    fun uDocuments(newDocuments: DocumentsState) = copy(documents = newDocuments)

    fun uScreenState(newScreenState: ScreenState) = copy(screen = newScreenState)

    fun uOpen(newOpen: Boolean) = copy(citizenship = citizenship.uOpen(newOpen))

    fun uSelected(newSelected: Citizenship) = copy(citizenship = citizenship.uSelected(newSelected))

    fun uCitizenship(newCitizenship: Citizenship) =
        copy(citizenship = citizenship.uCitizenship(newCitizenship))

}