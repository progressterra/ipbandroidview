package com.progressterra.ipbandroidview.pages.pickuppoint

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.widgets.pickupchoose.PickUpChooseEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PickUpPointViewModel(
    private val checkPermissionUseCase: CheckPermissionUseCase
) : ViewModel(), ContainerHost<PickUpPointState, PickUpPointEffect>, UsePickUpPoint {

    override val container: Container<PickUpPointState, PickUpPointEffect> =
        container(PickUpPointState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    fun refresh(points: List<PickUpPointInfo>) {
        intent {
            val result = checkPermissionUseCase(locationPermission).isSuccess
            reduce { state.uPermission(result).uPoints(points) }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(PickUpPointEffect.Back)
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event) {
                is ButtonEvent.Click -> state.choose.currentPickUpPointInfo?.let {
                    postSideEffect(PickUpPointEffect.Next(it))
                }
            }
        }
    }

    override fun handle(event: PickUpChooseEvent) {
        intent {
            when (event) {
                is PickUpChooseEvent.Choose -> reduce { state.uCurrentPoint(event.info) }
            }
        }
    }
}
