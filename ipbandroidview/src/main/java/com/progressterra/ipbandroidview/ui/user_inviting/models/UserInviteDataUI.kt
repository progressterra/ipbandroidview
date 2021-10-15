package com.progressterra.ipbandroidview.ui.user_inviting.models

import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.invite_members.AmbassadorInviteDataResponse

data class UserInviteDataUI(val promocode: String, val textInvite: String)

internal fun AmbassadorInviteDataResponse.InviteData.toUiModel(): UserInviteDataUI {
    return UserInviteDataUI(this.promocode ?: "", this.textInvite ?: "")
}