package com.progressterra.ipbandroidview.shared.ui.textfield

import com.progressterra.ipbandroidview.entities.Id

sealed class TextFieldEvent(override val id: String) : Id {

    class TextChanged(id: String, val text: String) : TextFieldEvent(id)

    class Action(id: String) : TextFieldEvent(id)

    class AdditionalAction(id: String) : TextFieldEvent(id)
}