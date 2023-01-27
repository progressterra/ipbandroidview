package com.progressterra.ipbandroidview.ui.referral

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.CopyTextUseCase
import com.progressterra.ipbandroidview.domain.usecase.ShareTextUseCase
import com.progressterra.ipbandroidview.domain.usecase.ambassador.InviteUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReferralViewModel(
    private val inviteUseCase: InviteUseCase,
    private val shareTextUseCase: ShareTextUseCase,
    private val copyTextUseCase: CopyTextUseCase
) : ViewModel(), ContainerHost<ReferralState, ReferralEffect>, ReferralInteractor {

    override val container: Container<ReferralState, ReferralEffect> = container(ReferralState())

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        inviteUseCase().onSuccess {
            reduce { state.copy(userInvite = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun copy() = intent {
        copyTextUseCase(state.userInvite.text)
        postSideEffect(ReferralEffect.Toast(R.string.success_copied))
    }

    override fun share() = intent { shareTextUseCase(state.userInvite.text) }

    override fun onBack() = intent { postSideEffect(ReferralEffect.Back) }
}