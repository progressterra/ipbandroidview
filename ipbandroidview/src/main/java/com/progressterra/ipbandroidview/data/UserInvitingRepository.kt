package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ipbAmbassador.IPBAmbassador
import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.invite_members.InvitingMembersRequest
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteDataUI
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteResultUI
import com.progressterra.ipbandroidview.ui.user_inviting.models.toUiModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal class UserInvitingRepository(val ambassadorInvite: IPBAmbassador.AmbassadorInvite) :
    BaseRepository(), IRepository.UserInviting {

    override suspend fun getInviteInfo(): SResult<UserInviteDataUI> {
        val tokenResult = getAccessToken()
        val token: String
        when (tokenResult) {
            is SResult.Success -> token = tokenResult.data
            else -> return emptyFailed()
        }

        val response = ambassadorInvite.getInviteInfo(token)
        return response.inviteData?.toUiModel()?.toSuccessResult()
            ?: response.result?.message.toFailedResult()
    }

    override suspend fun sendInvites(invitingMembersRequest: InvitingMembersRequest): SResult<UserInviteResultUI> {
        val response = ambassadorInvite.sendInvites(invitingMembersRequest)
        return response.dataInviteByPhone?.toUiModel()?.toSuccessResult()
            ?: response.result?.message.toFailedResult()
    }
}