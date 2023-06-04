package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

data class AdaptiveEntry(
    val status: TypeStatusDoc,
    val name: String,
    override val id: String,
    val text: TextFieldState = TextFieldState(),
    val makePhoto: MakePhotoState? = null
) : Id