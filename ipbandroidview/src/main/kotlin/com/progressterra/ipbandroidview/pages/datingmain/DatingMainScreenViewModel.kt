package com.progressterra.ipbandroidview.pages.datingmain

import android.Manifest
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.dating.AvailableTargetsUseCase
import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.dating.ReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.UpdateDatingLocationUseCase
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.processes.location.LocationToLocationPointUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class DatingMainScreenViewModel(
    private val deleteReadyToMeetUseCase: DeleteReadyToMeetUseCase,
    private val readyToMeetUseCase: ReadyToMeetUseCase,
    private val usersAroundUseCase: UsersAroundUseCase,
    private val locationToLocationPointUseCase: LocationToLocationPointUseCase,
    private val updateDatingLocationUseCase: UpdateDatingLocationUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val provideLocationUseCase: ProvideLocationUseCase,
    private val availableTargets: AvailableTargetsUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val makeToastUseCase: MakeToastUseCase
) : UseDatingMainScreen,
    AbstractNonInputViewModel<DatingMainScreenState, DatingMainScreenEffect>() {

    init {
        onBackground {
            usersAroundUseCase.resultFlow.collect { result ->
                result.onSuccess { anotherUsers -> emitState { it.copy(users = anotherUsers) } }
            }
        }
    }

    private var updateJob: Job? = null

    override fun createInitialState() = DatingMainScreenState()

    override fun handle(event: DatingMainScreenEvent) {
        onBackground {
            when (event) {
                is DatingMainScreenEvent.OnOwnProfile -> postEffect(
                    DatingMainScreenEffect.OnProfile(
                        currentState.currentUser
                    )
                )

                is DatingMainScreenEvent.OnProfile -> postEffect(
                    DatingMainScreenEffect.OnProfile(
                        event.user
                    )
                )

                is DatingMainScreenEvent.SelectTarget -> {
                    emitState {
                        it.copy(
                            readyToMeet = it.readyToMeet.copy(enabled = true),
                            chosenTarget = event.data
                        )
                    }
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            availableTargets().onSuccess { targets ->
                emitState { it.copy(datingTargets = targets) }
            }
            fetchDatingUserUseCase().onSuccess { user ->
                emitState { it.copy(currentUser = user) }
            }
        }

    }

    private fun startLocationUpdates() {
        updateJob?.cancel()
        updateJob = onBackground {
            while (true) {
                delay(1 * 60 * 1000)
                provideLocationUseCase().onSuccess { location ->
                    locationToLocationPointUseCase(location).onSuccess { point ->
                        updateDatingLocationUseCase(point).onSuccess {
                            makeToastUseCase(R.string.location_updated)
                            emitState { it.copy(currentUser = it.currentUser.copy(locationPoint = point)) }
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: BrushedSwitchEvent) {
        onBackground {
            val readyToMeet = !currentState.readyToMeet.turned
            if (readyToMeet) {
                checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                    provideLocationUseCase().onSuccess { location ->
                        locationToLocationPointUseCase(location).onSuccess { point ->
                            readyToMeetUseCase(point, currentState.chosenTarget!!).onSuccess {
                                emitState {
                                    it.copy(
                                        readyToMeet = it.readyToMeet.copy(turned = true),
                                        currentUser = it.currentUser.copy(locationPoint = point)
                                    )
                                }
                                startLocationUpdates()
                            }
                        }
                    }
                }.onFailure {
                    makeToastUseCase(R.string.failure_permission)
                    askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            } else {
                deleteReadyToMeetUseCase().onSuccess {
                    emitState { it.copy(readyToMeet = it.readyToMeet.copy(turned = false)) }
                    updateJob?.cancel()
                }
            }
        }
    }
}