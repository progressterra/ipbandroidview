package com.progressterra.ipbandroidview.ui.pickuppoint

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidview.core.ManagePermissionContract
import com.progressterra.ipbandroidview.model.PickUpPointInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PickUpPointViewModel(
    private val managePermissionContract: ManagePermissionContract.Client
) : ViewModel(), ContainerHost<PickUpPointState, PickUpPointEffect> {

    override val container: Container<PickUpPointState, PickUpPointEffect> =
        container(PickUpPointState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    init {
        viewModelScope.launch {
            delay(3000)
            checkPermission()
        }
    }

    private fun checkPermission() = intent {
        val result = managePermissionContract.checkPermission(locationPermission)
        reduce { state.copy(isPermissionGranted = result) }
    }

    fun choose(pickUpPointInfo: PickUpPointInfo) = intent {
        reduce { state.copy(currentPickUpPointInfo = pickUpPointInfo) }
    }

    fun next() = intent {
        state.currentPickUpPointInfo?.let { postSideEffect(PickUpPointEffect.Next(it)) }
    }

    fun back() = intent {
        postSideEffect(PickUpPointEffect.Back)
    }
}