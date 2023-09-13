package com.progressterra.ipbandroidview.shared.ui.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidview.shared.ui.MaskVisualTransformation
import com.progressterra.ipbandroidview.shared.ui.Masks.DATE_MASK
import com.progressterra.ipbandroidview.shared.ui.Masks.PHONE_MASK

enum class TextInputType {
    DEFAULT,
    NUMBER,
    PHONE_NUMBER,
    DATE,
    EMAIL,
    NAME_SURNAME,
    CHAT;

    fun toVisualTransformation() = when (this) {
        PHONE_NUMBER -> MaskVisualTransformation(PHONE_MASK)
        DATE -> MaskVisualTransformation(DATE_MASK)
        else -> VisualTransformation.None
    }

    fun toKeyboardOptions() = when (this) {
        NUMBER -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        PHONE_NUMBER -> KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        CHAT -> KeyboardOptions.Default.copy(imeAction = ImeAction.Send)
        else -> KeyboardOptions.Default
    }

    fun toTypeValueCharacteristic() = when (this) {
        NUMBER -> TypeValueCharacteristic.AS_NUMBER
        DATE -> TypeValueCharacteristic.AS_DATE_TIME
        else -> TypeValueCharacteristic.AS_STRING
    }

    fun toAllowedChars() = when (this) {
        PHONE_NUMBER -> 11
        DATE -> 8
        else -> Int.MAX_VALUE
    }
}