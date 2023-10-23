package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

class DatingProfileScreenViewModel(
    private val changeInterestsUseCase: ChangeInterestsUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val saveDatingInfoUseCase: SaveDatingInfoUseCase,
    private val fetchInterestsUseCase: FetchInterestsUseCase
) : UseDatingProfileScreen,
    AbstractInputViewModel<DatingUser, DatingProfileScreenState, DatingProfileScreenEffect>() {

    override fun createInitialState() = DatingProfileScreenState()

    private fun refresh() {
        onBackground {
            if (currentState.ownProfile) {
                fetchDatingUserUseCase().onSuccess { newUser ->
                    emitState { it.copy(user = newUser) }
                }
            }
            fetchInterestsUseCase().onSuccess { allInterests ->
                emitState { it.copy(allInterests = allInterests) }
            }
        }
    }

    override fun handle(event: DatingProfileScreenEvent) {
        onBackground {
            when (event) {
                is DatingProfileScreenEvent.Edit -> {
                    emitState { it.copy(editMode = true) }
                    refresh()
                }

                is DatingProfileScreenEvent.OnSettings -> postEffect(DatingProfileScreenEffect.OnSettings)
                is DatingProfileScreenEvent.OnBack -> if (currentState.editMode) {
                    emitState { it.copy(editMode = false) }
                    refresh()
                } else {
                    postEffect(DatingProfileScreenEffect.OnBack)
                }

                is DatingProfileScreenEvent.PickInterest -> if (currentState.changedInterests.contains(
                        event.interest
                    )
                ) {
                    emitState { it.copy(changedInterests = it.changedInterests - event.interest) }
                } else {
                    emitState { it.copy(changedInterests = it.changedInterests + event.interest) }
                }
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "save") {
            onBackground {
                changeInterestsUseCase(currentState.changedInterests)
                saveDataUseCase(
                    EditUserState(
                        name = currentState.name,
                        email = currentState.email,
                        phone = currentState.phone,
                        birthday = currentState.birthday
                    )
                )
                saveDatingInfoUseCase(
                    nickName = currentState.nickName.text,
                    description = currentState.about.text
                )
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        if (event is TextFieldEvent.TextChanged) {
            if (event.id == "name") {
                emitState { it.copy(name = it.name.copy(text = event.text)) }
            }
            if (event.id == "birthday") {
                emitState { it.copy(birthday = it.birthday.copy(text = event.text)) }
            }
            if (event.id == "nickName") {
                emitState { it.copy(nickName = it.nickName.copy(text = event.text)) }
            }
            if (event.id == "about") {
                emitState { it.copy(about = it.about.copy(text = event.text)) }
            }
            if (event.id == "email") {
                emitState { it.copy(email = it.email.copy(text = event.text)) }
            }
        }
        valid()
    }

    override fun setup(data: DatingUser) {
        emitState { it.copy(user = data) }
    }

    private fun valid() = onBackground {
        val valid =
            currentState.name.valid() && currentState.birthday.valid()
        emitState { it.copy(save = it.save.copy(enabled = valid)) }
    }
}