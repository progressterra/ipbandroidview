package com.progressterra.ipbandroidview.shared.ui.textfield

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.core.text.isDigitsOnly
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumberWithoutHeading
import com.progressterra.ipbandroidview.shared.toDate
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class TextFieldState(
    override val id: String = "",
    val text: String = "",
    val placeholder: String? = null,
    val label: String? = null,
    val enabled: Boolean = true,
    val type: TextInputType = TextInputType.DEFAULT,
    val typeValue: TypeValueCharacteristic? = null
) : Id, Parcelable {

    fun toFieldData(id: String) = FieldData(
        idrfCharacteristicType = id,
        name = label,
        comment = placeholder,
        order = 0,
        typeValue = type.toTypeValueCharacteristic(),
        valueData = formatByType()
    )

    fun unFormatByType(data: String) = when (type) {
        TextInputType.PHONE_NUMBER -> copy(text = data.removePrefix("7"))
        TextInputType.DATE -> copy(text = data.replace(".", ""))
        else -> copy(text = data)
    }

    fun formatByType() = when (type) {
        TextInputType.DATE -> text.toDate()
        TextInputType.PHONE_NUMBER -> "7$text"
        else -> text.trim()
    }

    fun valid() = when (type) {
        TextInputType.DEFAULT -> true
        TextInputType.NUMBER -> text.isDigitsOnly()
        TextInputType.PHONE_NUMBER -> text.isRussianPhoneNumberWithoutHeading()
        TextInputType.DATE -> text.isDigitsOnly()
        TextInputType.CHAT -> true
    }

    companion object
}
