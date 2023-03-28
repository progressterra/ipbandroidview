package com.progressterra.ipbandroidview.shared.ui.textfield

sealed class TextFieldEvent(val id: String) {

    class TextChanged(id: String, val text: String) : TextFieldEvent(id)

    class Action(id: String) : TextFieldEvent(id)

    class AdditionalAction(id: String) : TextFieldEvent(id)
}