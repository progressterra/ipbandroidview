package com.progressterra.ipbandroidview.pages.locationpermission

import android.Manifest
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent

class LocationPermissionScreenViewModel(
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makeToastUseCase: MakeToastUseCase
) :
    AbstractNonInputViewModel<LocationPermissionScreenState, LocationPermissionScreenEffect>(),
    UseLocationPermissionScreen {

    override fun createInitialState() = LocationPermissionScreenState()

    override fun refresh() {
        checkAndAsk()
    }

    private fun checkAndAsk() {
        onBackground {
            checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                postEffect(LocationPermissionScreenEffect.OnNext)
            }.onFailure {
                askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                    makeToastUseCase(R.string.success_permission)
                }.onFailure {
                    makeToastUseCase(R.string.failure_permission)
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(LocationPermissionScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "give") {
            checkAndAsk()
        } else if (event.id == "skip") {
            postEffect(LocationPermissionScreenEffect.OnNext)
        }
    }
}