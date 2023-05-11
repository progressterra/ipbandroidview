package com.progressterra.ipbandroidview.ui.pickuppoint

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.model.PickUpPointInfo
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PickUpPointViewModel(
    private val checkPermissionUseCase: CheckPermissionUseCase
) : ViewModel(), ContainerHost<PickUpPointState, PickUpPointEffect>, PickUpPointInteractor {

    override val container: Container<PickUpPointState, PickUpPointEffect> =
        container(PickUpPointState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    init {
        initialCheckPermission()
    }

    private fun initialCheckPermission() = intent {
        delay(3000)
        checkPermission()
    }

    fun setPoints(points: List<PickUpPointInfo>) = intent {
        reduce { state.copy(pickUpPoints = points) }
    }

    private fun checkPermission() = intent {
        val result = checkPermissionUseCase(locationPermission).isSuccess
        reduce { state.copy(isPermissionGranted = result) }
    }

    override fun choose(info: PickUpPointInfo) = intent {
        reduce { state.copy(currentPickUpPointInfo = info) }
    }

    override fun onNext() = intent {
        state.currentPickUpPointInfo?.let { postSideEffect(PickUpPointEffect.Next(it)) }
    }

    override fun onBack() = intent {
        postSideEffect(PickUpPointEffect.Back)
    }
}