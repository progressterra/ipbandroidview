package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingConnection
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.AcceptConnectUseCase
import com.progressterra.ipbandroidview.processes.dating.ConnectUseCase
import com.progressterra.ipbandroidview.processes.dating.CreateChatWithUserUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class DatingProfileScreenViewModel(
    private val changeInterestsUseCase: ChangeInterestsUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
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

    init {
        onBackground {
            fetchDatingUserUseCase.resultFlow.collect { result ->
                result.onSuccess { newUser ->
                    emitState {
                        it.copy(
                            user = newUser,
                            allInterests = it.allInterests.map { interest ->
                                if (newUser.interests.contains(interest)) interest.copy(picked = true) else interest
                            },
                            nickName = it.nickName.copy(text = newUser.name),
                            about = it.about.copy(text = newUser.description)
                        )
                    }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        refresh()
    }

    fun refresh() {
        onBackground {
            emitState {
                it.copy(
                    screen = it.screen.copy(state = ScreenState.LOADING),
                    editMode = false
                )
            }
            var isSuccess = true
            fetchInterestsUseCase().onSuccess { allInterests ->
                emitState { it.copy(allInterests = allInterests) }
            }.onFailure { isSuccess = false }
            if (currentState.user.own) {
                fetchDatingUserUseCase()
            }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: DatingProfileScreenEvent) {
        onBackground {
            when (event) {
                is DatingProfileScreenEvent.Edit -> {
                    emitState { it.copy(editMode = true) }
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
                    pickPhotoUseCase().onSuccess { uri ->
                        saveAvatarUseCase(uri).onSuccess {
                            emitState { it.copy(user = it.user.copy(avatar = uri.toString())) }
                            makeToastUseCase(R.string.success)
                        }.onFailure {
                            makeToastUseCase(R.string.failure)
                        }
                    }
                }
            }
            if (event.id == "save") {
                changeInterestsUseCase(currentState.changedInterests)
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

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun setup(data: DatingUser) {
        emitState {
            it.copy(
                user = data,
                chat = it.chat.copy(enabled = data.connection == DatingConnection.CONNECTED),
                connect = it.connect.copy(enabled = data.connection == DatingConnection.CAN_CONNECT || data.connection == DatingConnection.REQUEST_RECEIVED)
            )
        }
    }

    private fun valid() = onBackground {
        val valid = currentState.nickName.text.isNotEmpty()
        emitState { it.copy(save = it.save.copy(enabled = valid)) }
    }
}