package com.progressterra.ipbandroidview.pages.datingmain

import android.Manifest
import com.progressterra.ipbandroidview.processes.dating.AvailableTargetsUseCase
import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.ReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.UpdateDatingLocationUseCase
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.processes.location.LocationToLocationPointUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
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
    private val availableTargets: AvailableTargetsUseCase
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

    }

    override fun refresh() {
        onBackground {
            availableTargets().onSuccess { targets ->
                emitState { it.copy(datingTargets = targets) }
            }
        }
    }

    private fun startLocationUpdates() {
        updateJob = onBackground {
            while (true) {
                provideLocationUseCase().getOrNull()?.let { location ->
                    locationToLocationPointUseCase(location).getOrNull()?.let { point ->
                        updateDatingLocationUseCase(point)
                    }
                }
                delay(1 * 60 * 1000)
            }
        }
    }

    private fun stopLocationUpdates() {
        updateJob?.cancel()
    }

    override fun handle(event: BrushedSwitchEvent) {
        onBackground {
            val readyToMeet = !currentState.readyToMeet.turned
            if (readyToMeet) {
                checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                    provideLocationUseCase().getOrNull()?.let { location ->
                        locationToLocationPointUseCase(location).getOrNull()?.let { point ->
                            emitState { it.copy(readyToMeet = it.readyToMeet.copy(turned = true)) }
                            readyToMeetUseCase(point, currentState.chosenTarget!!)
                            startLocationUpdates()
                        }
                    }
                }.onFailure { askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION) }
            } else {
                deleteReadyToMeetUseCase().onSuccess {
                    emitState { it.copy(readyToMeet = it.readyToMeet.copy(turned = false)) }
                    stopLocationUpdates()
                }
            }
        }
    }
}