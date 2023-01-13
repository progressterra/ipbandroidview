package com.progressterra.ipbandroidview.ui.referral

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.core.StartActivityContract
import com.progressterra.ipbandroidview.domain.usecase.ambassador.InviteUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReferralViewModel(
    private val inviteUseCase: InviteUseCase,
    private val clipboardManager: ClipboardManager,
    private val startActivity: StartActivityContract.Client
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
        clipboardManager.setPrimaryClip(ClipData.newPlainText("invite text", state.userInvite.text))
        postSideEffect(ReferralEffect.Toast(R.string.success_copied))
    }

    override fun share() = intent {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, state.userInvite.text)
        sendIntent.type = "text/plain"
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity.start(shareIntent)
    }

    override fun onBack() = intent {
        postSideEffect(ReferralEffect.Back)
    }
}