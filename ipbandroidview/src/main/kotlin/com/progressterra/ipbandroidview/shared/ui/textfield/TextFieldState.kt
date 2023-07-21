package com.progressterra.ipbandroidview.shared.ui.textfield

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.core.text.isDigitsOnly
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
import com.progressterra.ipbandroidview.shared.toDate
import com.progressterra.processors.IpbState
import kotlinx.parcelize.Parcelize

@Immutable
@IpbState
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

    fun formatByType() = when (type) {
        TextInputType.DATE -> text.toDate()
        TextInputType.PHONE_NUMBER -> "7$text"
        else -> text
    }

    fun valid() = when (type) {
        TextInputType.DEFAULT -> true
        TextInputType.NUMBER -> text.isDigitsOnly()
        TextInputType.PHONE_NUMBER -> text.isRussianPhoneNumber()
        TextInputType.DATE -> text.isDigitsOnly()
        TextInputType.CHAT -> true
    }
}
