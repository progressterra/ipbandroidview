package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ipbAmbassador.IPBAmbassador
import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.invite_members.InvitingMembersRequest
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteDataUI
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteResultUI
import com.progressterra.ipbandroidview.ui.user_inviting.models.toUiModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

internal class UserInvitingRepository(val ambassadorInvite: IPBAmbassador.AmbassadorInvite) :
    BaseRepository(), IRepository.UserInviting {

    override suspend fun getInviteInfo(): SResult<UserInviteDataUI> = safeApiCall {
        val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }

        val response = ambassadorInvite.getInviteInfo(token)
        response.inviteData?.toUiModel()?.toSuccessResult()
            ?: response.responseToFailedResult()
    }

    override suspend fun sendInvites(invitingMembersRequest: InvitingMembersRequest): SResult<UserInviteResultUI> {
        val response = ambassadorInvite.sendInvites(invitingMembersRequest)
        return response.dataInviteByPhone?.toUiModel()?.toSuccessResult()
            ?: response.responseToFailedResult()
    }
}