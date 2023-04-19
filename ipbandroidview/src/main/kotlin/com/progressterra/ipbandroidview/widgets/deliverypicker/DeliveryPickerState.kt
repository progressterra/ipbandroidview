package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class DeliveryPickerState(
    val addressUI: AddressUI = AddressUI(),
    val selectedDeliveryMethod: Delivery? = null,
    val deliveryMethods: List<Delivery> = emptyList(),
    val city: TextFieldState = TextFieldState(),
    val home: TextFieldState = TextFieldState(),
    val entrance: TextFieldState = TextFieldState(),
    val apartment: TextFieldState = TextFieldState(),
    val address: TextFieldState = TextFieldState(),
    val selectPoint: ButtonState = ButtonState()
) {

    fun updatePickUpPointInfo(info: PickUpPointInfo) =
        if (selectedDeliveryMethod is Delivery.PickUpPointDelivery) {
            copy(selectedDeliveryMethod = selectedDeliveryMethod.updateCurrentPoint(info))
        } else this

    fun updateSelectedMethod(delivery: Delivery) = copy(selectedDeliveryMethod = delivery)

    fun updateCity(newCity: String) = copy(city = city.updateText(newCity))

    fun updateHome(newHome: String) = copy(home = home.updateText(newHome))

    fun updateEntrance(newEntrance: String) = copy(entrance = entrance.updateText(newEntrance))

    fun updateApartment(newApartment: String) = copy(apartment = apartment.updateText(newApartment))
}