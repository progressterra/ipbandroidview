package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AdaptiveEntry
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.citizenshipsuggestions.CitizenshipSuggestionsState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.updateById
import com.progressterra.processors.IpbSubState

@Immutable
data class EditUserState(
    @IpbSubState val name: TextFieldState = TextFieldState(id = "name"),
    @IpbSubState val email: TextFieldState = TextFieldState(id = "email"),
    @IpbSubState val phone: TextFieldState = TextFieldState(id = "phone"),
    @IpbSubState val birthday: TextFieldState = TextFieldState(id = "birthday")
)