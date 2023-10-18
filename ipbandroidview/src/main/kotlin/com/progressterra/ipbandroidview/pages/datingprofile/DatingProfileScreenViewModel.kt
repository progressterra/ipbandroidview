package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel

class DatingProfileScreenViewModel : UseDatingProfileScreen,
    AbstractInputViewModel<DatingUser, DatingProfileScreenState, DatingProfileScreenEffect>() {

    override fun createInitialState() = DatingProfileScreenState()

    override fun handle(event: DatingProfileScreenEvent) {
        when (event) {
            is DatingProfileScreenEvent.AcceptRequest -> Unit
            is DatingProfileScreenEvent.OnBack -> postEffect(DatingProfileScreenEffect.OnBack)
            is DatingProfileScreenEvent.OnToChat -> postEffect(DatingProfileScreenEffect.OnToChat)
            is DatingProfileScreenEvent.SendRequest -> Unit
        }
    }

    override fun setup(data: DatingUser) {
        emitState { it.copy(user = data) }
    }
}