package com.progressterra.ipbandroidview.shared.ui.textfield

import android.os.Parcelable
import androidx.core.text.isDigitsOnly
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.isEmail
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
import com.progressterra.ipbandroidview.shared.toDate
import kotlinx.parcelize.Parcelize


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
        TextInputType.DATE -> copy(text = data.replace(".", ""))
        else -> copy(text = data)
    }

    fun formatByType() = when (type) {
        TextInputType.DATE -> text.toDate()
        else -> text.trim()
    }

    fun valid() = when (type) {
        TextInputType.NUMBER -> text.trim().isDigitsOnly()
        TextInputType.PHONE_NUMBER -> text.trim().isRussianPhoneNumber()
        TextInputType.DATE -> text.trim().isDigitsOnly() && text.length == 8
        TextInputType.EMAIL -> text.trim().isEmail()
        TextInputType.NAME_SURNAME -> text.trim().split(" ").size == 2
        else -> true
    }

    companion object
}
