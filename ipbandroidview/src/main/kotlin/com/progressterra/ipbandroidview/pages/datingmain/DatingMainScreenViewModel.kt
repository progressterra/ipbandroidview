package com.progressterra.ipbandroidview.pages.datingmain

import android.Manifest
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.processes.dating.AvailableTargetsUseCase
import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.dating.ReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.UpdateDatingLocationUseCase
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.log
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class DatingMainScreenViewModel(
    private val deleteReadyToMeetUseCase: DeleteReadyToMeetUseCase,
    private val readyToMeetUseCase: ReadyToMeetUseCase,
    private val usersAroundUseCase: UsersAroundUseCase,
    private val updateDatingLocationUseCase: UpdateDatingLocationUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val availableTargets: AvailableTargetsUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val makeToastUseCase: MakeToastUseCase
) : UseDatingMainScreen,
    AbstractNonInputViewModel<DatingMainScreenState, DatingMainScreenEffect>() {

    private var lastTimeUpdated: Long = 0

    private val updateInterval = 3 * 60 * 1000

    init {
        onBackground {
            usersAroundUseCase.resultFlow.collect { result ->
                result.onSuccess { anotherUsers -> emitState { it.copy(users = anotherUsers) } }
                    .onFailure {
                        emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                    }
            }
        }
        onBackground {
            fetchDatingUserUseCase.resultFlow.collect { result ->
                result.onSuccess { newCurrent ->
                    emitState {
                        it.copy(
                            currentUser = newCurrent,
                            readyToMeet = it.readyToMeet.copy(turned = newCurrent.readyToMeet)
                        )
                    }
                    val currentTime = System.currentTimeMillis()
                    if (newCurrent.readyToMeet && currentTime - lastTimeUpdated >= updateInterval) {
                        lastTimeUpdated = currentTime
                        updateDatingLocationUseCase().onSuccess {
                            makeToastUseCase(R.string.location_updated)
                            usersAroundUseCase()
                            fetchDatingUserUseCase()
                        }
                    }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun createInitialState() = DatingMainScreenState()

    override fun handle(event: DatingMainScreenEvent) {
        onBackground {
            when (event) {
                is DatingMainScreenEvent.OnOwnProfile -> postEffect(
                    DatingMainScreenEffect.OnOwnProfile
                )

                is DatingMainScreenEvent.OnProfile -> postEffect(
                    DatingMainScreenEffect.OnProfile(event.user)
                )

                is DatingMainScreenEvent.SelectTarget -> {
                    checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                        readyToMeetUseCase(event.data).onSuccess {
                            fetchDatingUserUseCase()
                            usersAroundUseCase()
                        }
                    }.onFailure {
                        makeToastUseCase(R.string.failure_location_permission)
                        askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            log("MAIN", "Loading")
            var isSuccess = true
            availableTargets().onSuccess { targets ->
                emitState { it.copy(datingTargets = targets) }
            }.onFailure { isSuccess = false }
            fetchDatingUserUseCase()
            log("MAIN", "Loaded with: $isSuccess")
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }

    }


    override fun handle(event: BrushedSwitchEvent) {
        onBackground {
            if (!currentState.readyToMeet.turned) {
                checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                    readyToMeetUseCase(currentState.currentUser.target).onSuccess {
                        fetchDatingUserUseCase()
                        usersAroundUseCase()
                    }
                }.onFailure {
                    makeToastUseCase(R.string.failure_location_permission)
                    askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            } else {
                deleteReadyToMeetUseCase().onSuccess {
                    fetchDatingUserUseCase()
                }
            }
        }
    }
}