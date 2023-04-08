package com.progressterra.ipbandroidview.pages.pickuppoint

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarEvent
import com.progressterra.ipbandroidview.processes.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
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

    fun refresh(points: List<PickUpPointInfo>) = intent {
        val result = checkPermissionUseCase(locationPermission).isSuccess
        reduce { state.updatePermission(result).updatePoints(points) }
    }

    override fun handle(event: ProshkaTopBarEvent) = intent {
        super.handle(event)
    }

    override fun handle(event: ButtonEvent) = intent {
        TODO("Not yet implemented")
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        TODO("Not yet implemented")
    }

    override fun handle(event: PickUpChooseEvent) = intent {
        TODO("Not yet implemented")
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