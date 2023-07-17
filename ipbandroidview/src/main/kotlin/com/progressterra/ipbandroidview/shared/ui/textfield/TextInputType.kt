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
        DEFAULT -> TypeValueCharacteristic.AS_STRING
        NUMBER -> TypeValueCharacteristic.AS_NUMBER
        PHONE_NUMBER -> TypeValueCharacteristic.AS_STRING
        CHAT -> TypeValueCharacteristic.AS_STRING
        DATE -> TypeValueCharacteristic.AS_DATE_TIME
    }

    fun toAllowedChars() = when (this) {
        PHONE_NUMBER -> 10
        DATE -> 8
        else -> Int.MAX_VALUE
    }
}