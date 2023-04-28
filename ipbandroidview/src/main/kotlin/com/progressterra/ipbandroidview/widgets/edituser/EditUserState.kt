package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class EditUserState(
    @IpbSubState val name: TextFieldState = TextFieldState(id = "name"),
    @IpbSubState val email: TextFieldState = TextFieldState(id = "email"),
    @IpbSubState val phone: TextFieldState = TextFieldState(id = "phone"),
    @IpbSubState val birthday: TextFieldState = TextFieldState(id = "birthday"),
    @IpbSubState val citizenship: TextFieldState = TextFieldState(id = "citizenship"),
    @IpbSubState val address: TextFieldState = TextFieldState(id = "address"),
    @IpbSubState val passport: TextFieldState = TextFieldState(id = "passport"),
    @IpbSubState val passportProvider: TextFieldState = TextFieldState(id = "passportProvider"),
    @IpbSubState val passportCode: TextFieldState = TextFieldState(id = "passportProviderCode"),
    @IpbSubState val patent: TextFieldState = TextFieldState(id = "patent"),
    val makePhoto: MakePhotoState = MakePhotoState()
) {

    fun addPhoto(item: MultisizedImage) = copy(makePhoto = makePhoto.add(item))

    fun removePhoto(item: MultisizedImage) = copy(makePhoto = makePhoto.remove(item))
}