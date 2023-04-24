package com.progressterra.ipbandroidview.pages.delivery

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerEvent
import com.progressterra.ipbandroidview.widgets.deliverypicker.FetchAvailableDeliveryUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class DeliveryViewModel(
    private val fetchAvailableDeliveryUseCase: FetchAvailableDeliveryUseCase,
    private val createDeliveryOrderUseCase: CreateDeliveryOrderUseCase
) : ViewModel(), ContainerHost<DeliveryState, DeliveryEvent>, UseDelivery {

    override val container = container<DeliveryState, DeliveryEvent>(DeliveryState())

    fun updatePickUpPoint(info: PickUpPointInfo) = intent {
        reduce { state.updatePickUpPoint(info) }
    }

    fun refresh() = intent {
        reduce { DeliveryState().updateConfirmEnabled(false) }
        fetchAvailableDeliveryUseCase().onSuccess {
            reduce { state.updateStateBoxState(ScreenState.SUCCESS).updateDeliveryPickerState(it) }
        }.onFailure {
            reduce { state.updateStateBoxState(ScreenState.ERROR) }
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(DeliveryEvent.Back)
        }
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event) {
            is ButtonEvent.Click -> when (event.id) {
                "confirm" -> createDeliveryOrderUseCase(
                    state.commentary.text, state.deliveryPicker.selectedDeliveryMethod!!
                ).onSuccess {
                    postSideEffect(DeliveryEvent.Next)
                }

                "selectPoint" -> postSideEffect(DeliveryEvent.SelectPickupPoint((state.deliveryPicker.selectedDeliveryMethod as Delivery.PickUpPointDelivery).points))
            }
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event) {
            is TextFieldEvent.TextChanged -> when (event.id) {
                "city" -> reduce { state.updateCity(event.text) }
                "home" -> reduce { state.updateHome(event.text) }
                "entrance" -> reduce { state.updateEntrance(event.text) }
                "apartment" -> reduce { state.updateApartment(event.text) }
                "commentary" -> reduce { state.updateCommentary(event.text) }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "city" -> reduce { state.updateCity("") }
                "home" -> reduce { state.updateHome("") }
                "entrance" -> reduce { state.updateEntrance("") }
                "apartment" -> reduce {
                    state.updateApartment("")
                }
            }
        }
    }

    override fun handle(event: DeliveryPickerEvent) = intent {
        when (event) {
            is DeliveryPickerEvent.SelectDeliveryMethod -> reduce {
                state.updateDeliveryMethod(event.delivery).updateConfirmEnabled(true)
            }
        }
    }
}