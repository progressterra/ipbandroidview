package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class DatingProfileScreenViewModel(
    private val fetchDatingUserUseCase: FetchDatingUserUseCase
) : UseDatingProfileScreen,
    AbstractInputViewModel<DatingUser, DatingProfileScreenState, DatingProfileScreenEffect>() {

    override fun createInitialState() = DatingProfileScreenState()

    override fun handle(event: DatingProfileScreenEvent) {
        onBackground {
            when (event) {
                is DatingProfileScreenEvent.Edit -> emitState { it.copy(editMode = true) }
                is DatingProfileScreenEvent.OnSettings -> postEffect(DatingProfileScreenEffect.OnSettings)
                is DatingProfileScreenEvent.OnBack -> postEffect(DatingProfileScreenEffect.OnBack)
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: TextFieldEvent) {
        TODO("Not yet implemented")
    }

    override fun setup(data: DatingUser) {
        emitState { it.copy(user = data) }
    }
}