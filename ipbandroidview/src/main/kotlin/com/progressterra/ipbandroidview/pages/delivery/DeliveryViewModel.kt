package com.progressterra.ipbandroidview.pages.delivery

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.SaveAddressUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerValidUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class DeliveryViewModel(
    private val deliveryPickerValidUseCase: DeliveryPickerValidUseCase,
    private val saveAddressUseCase: SaveAddressUseCase,
    private val commentUseCase: CommentUseCase
) : ViewModel(), ContainerHost<DeliveryState, DeliveryEvent>, UseDelivery {

    override val container = container<DeliveryState, DeliveryEvent>(DeliveryState())

    fun refresh() {
        intent {
            reduce {
                state.uConfirmEnabled(false).uDeliveryPickerCityText(UserData.address.nameCity)
                    .uDeliveryPickerEntranceText(UserData.address.entrance)
                    .uDeliveryPickerApartmentText(UserData.address.apartment)
                    .uDeliveryPickerHomeText(UserData.address.houseNUmber).uCommentaryText("")
            }

        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(DeliveryEvent.Back)
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event) {
                is ButtonEvent.Click -> when (event.id) {
                    "confirm" -> {
                        var isSuccess = true
                        saveAddressUseCase(
                            city = state.deliveryPicker.city.text,
                            home = state.deliveryPicker.home.text,
                            entrance = state.deliveryPicker.entrance.text,
                            apartment = state.deliveryPicker.apartment.text
                        ).onFailure {
                            isSuccess = false
                        }
                        commentUseCase(state.commentary.text).onFailure {
                            isSuccess = false
                        }
                        if (isSuccess) {
                            postSideEffect(DeliveryEvent.Next)
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.TextChanged -> when (event.id) {
                    "city" -> reduce { state.uDeliveryPickerCityText(event.text) }
                    "home" -> reduce { state.uDeliveryPickerHomeText(event.text) }
                    "entrance" -> reduce { state.uDeliveryPickerEntranceText(event.text) }
                    "apartment" -> reduce { state.uDeliveryPickerApartmentText(event.text) }
                    "commentary" -> reduce { state.uCommentaryText(event.text) }
                }

                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "city" -> reduce { state.uDeliveryPickerCityText("") }
                    "home" -> reduce { state.uDeliveryPickerHomeText("") }
                    "entrance" -> reduce { state.uDeliveryPickerEntranceText("") }
                    "apartment" -> reduce { state.uDeliveryPickerApartmentText("") }
                }
            }
            checkValid()
        }
    }

    private fun checkValid() {
        intent {
            val valid = deliveryPickerValidUseCase(state.deliveryPicker).isSuccess
            reduce { state.uConfirmEnabled(valid) }
        }
    }
}