package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ipbAmbassador.IPBAmbassador
import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.invite_members.InvitingMembersRequest
import com.progressterra.ipbandroidview.ui.contacts.models.UserInviteDataUI
import com.progressterra.ipbandroidview.ui.contacts.models.UserInviteResultUI
import com.progressterra.ipbandroidview.ui.contacts.models.toUiModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal class UserInvitingRepository(val ambassadorInvite: IPBAmbassador.AmbassadorInvite) :
    BaseRepository(), IRepository.UserInviting {

    override suspend fun getInviteInfo(accessToken: String): SResult<UserInviteDataUI> {
        val response = ambassadorInvite.getInviteInfo(accessToken)
        return response.inviteData?.toUiModel()?.toSuccessResult()
            ?: response.result?.message.toFailedResult()
    }

    override suspend fun sendInvites(invitingMembersRequest: InvitingMembersRequest): SResult<UserInviteResultUI> {
        val response = ambassadorInvite.sendInvites(invitingMembersRequest)
        return response.dataInviteByPhone?.toUiModel()?.toSuccessResult()
            ?: response.result?.message.toFailedResult()
    }
}