package com.progressterra.ipbandroidview.pages.locationpermission

import android.Manifest
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class LocationPermissionScreenViewModel(
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makeToastUseCase: MakeToastUseCase
) :
    AbstractNonInputViewModel<LocationPermissionScreenState, LocationPermissionScreenEffect>(),
    UseLocationPermissionScreen {

    private var checkJob: Job? = null

    override fun createInitialState() = LocationPermissionScreenState()

    override fun refresh() {
        checkAndAsk()
        checkBackground()
    }

    private fun checkAndAsk() {
        onBackground {
            checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                checkJob?.cancel()
                postEffect(LocationPermissionScreenEffect.OnNext)
            }.onFailure {
                askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun checkBackground() {
        checkJob?.cancel()
        checkJob = onBackground {
            while (true) {
                checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                    makeToastUseCase(R.string.success_permission)
                    postEffect(LocationPermissionScreenEffect.OnNext)
                    checkJob?.cancel()
                }
                delay(3000)
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(LocationPermissionScreenEffect.OnBack)
        checkJob?.cancel()
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "give") {
            checkAndAsk()
        } else if (event.id == "skip") {
            checkJob?.cancel()
            postEffect(LocationPermissionScreenEffect.OnNext)
        }
    }
}