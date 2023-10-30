package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingConnection
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.dating.AcceptConnectUseCase
import com.progressterra.ipbandroidview.processes.dating.ConnectUseCase
import com.progressterra.ipbandroidview.processes.dating.CreateChatWithUserUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

class DatingProfileScreenViewModel(
    private val changeInterestsUseCase: ChangeInterestsUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val saveDatingInfoUseCase: SaveDatingInfoUseCase,
    private val fetchInterestsUseCase: FetchInterestsUseCase,
    private val connectUseCase: ConnectUseCase,
    private val acceptConnectUseCase: AcceptConnectUseCase,
    private val createChatWithUserUseCase: CreateChatWithUserUseCase,
    private val pickPhotoUseCase: PickPhotoUseCase,
    private val makeToastUseCase: MakeToastUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase
) : UseDatingProfileScreen,
    AbstractInputViewModel<DatingUser, DatingProfileScreenState, DatingProfileScreenEffect>() {

    override fun createInitialState() = DatingProfileScreenState()

    fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
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
        onBackground {
            if (event.id == "choosePhoto") {
                onBackground {
                    pickPhotoUseCase().onSuccess { path ->
                        saveAvatarUseCase(path).onSuccess {
                            makeToastUseCase(R.string.success)
                            refresh()
                        }.onFailure {
                            makeToastUseCase(R.string.failure)
                        }
                    }
                }
            }
            if (event.id == "save") {
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
                    nickName = currentState.nickName.formatByType(),
                    description = currentState.about.formatByType()
                )

            }
            if (event.id == "connect") {
                if (currentState.user.connection == DatingConnection.CAN_CONNECT) {
                    connectUseCase(currentState.user)
                }
                if (currentState.user.connection == DatingConnection.REQUEST_RECEIVED) {
                    acceptConnectUseCase(currentState.user)
                }
            }
            if (event.id == "chat") {
                createChatWithUserUseCase(currentState.user).onSuccess {
                    postEffect(DatingProfileScreenEffect.OnChat(it))
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        if (event is TextFieldEvent.TextChanged) {
            if (event.id == "nickName") {
                emitState { it.copy(nickName = it.nickName.copy(text = event.text)) }
            }
            if (event.id == "about") {
                emitState { it.copy(about = it.about.copy(text = event.text)) }
            }
        }
        if (event is TextFieldEvent.AdditionalAction) {
            if (event.id == "nickName") {
                emitState { it.copy(nickName = it.nickName.copy(text = "")) }
            }
            if (event.id == "about") {
                emitState { it.copy(about = it.about.copy(text = "")) }
            }
        }
        valid()
    }

    override fun setup(data: DatingUser) {
        emitState {
            it.copy(
                user = data,
                chat = it.chat.copy(enabled = data.connection == DatingConnection.CONNECTED),
                connect = it.connect.copy(enabled = data.connection == DatingConnection.CAN_CONNECT || data.connection == DatingConnection.REQUEST_RECEIVED),
                ownProfile = data.isEmpty()
            )
        }
        refresh()
    }

    private fun valid() = onBackground {
        val valid = currentState.nickName.text.isNotEmpty()
        emitState { it.copy(save = it.save.copy(enabled = valid)) }
    }
}