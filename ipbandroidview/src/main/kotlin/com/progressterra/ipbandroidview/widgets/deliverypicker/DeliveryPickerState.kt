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
    val city: TextFieldState = TextFieldState(id = "city"),
    val home: TextFieldState = TextFieldState(id = "home"),
    val entrance: TextFieldState = TextFieldState(id = "entrance"),
    val apartment: TextFieldState = TextFieldState(id = "apartment"),
    val address: TextFieldState = TextFieldState(id = "address"),
    val selectPoint: ButtonState = ButtonState(id = "selectPoint")
) {

    fun uPickUpPointInfo(info: PickUpPointInfo) =
        if (selectedDeliveryMethod is Delivery.PickUpPointDelivery) {
            copy(selectedDeliveryMethod = selectedDeliveryMethod.uCurrentPoint(info))
        } else this

    fun uSelectedMethod(delivery: Delivery) = copy(selectedDeliveryMethod = delivery)

    fun uCity(newCity: String) = copy(city = city.uText(newCity))

    fun uHome(newHome: String) = copy(home = home.uText(newHome))

    fun uEntrance(newEntrance: String) = copy(entrance = entrance.uText(newEntrance))

    fun uApartment(newApartment: String) = copy(apartment = apartment.uText(newApartment))
}