package com.progressterra.ipbandroidview.ui.contacts.models

import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.invite_members.InviteByPhoneResponse

data class UserInviteResultUI(
    val listSuccessfulInvite: List<String>,
    val listRejectedInvite: List<String>
)

internal fun InviteByPhoneResponse.DataInviteByPhone.toUiModel(): UserInviteResultUI {
    return UserInviteResultUI(
        this.listSuccessfulInvite ?: emptyList(),
        this.listRejectedInvite ?: emptyList()
    )
}