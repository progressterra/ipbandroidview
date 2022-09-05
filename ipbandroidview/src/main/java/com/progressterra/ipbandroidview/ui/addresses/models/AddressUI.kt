package com.progressterra.ipbandroidview.ui.addresses.models


data class AddressUI(
    var idUnique: String = "",
    var idClient: String = "",
    var dateAdded: String = "",
    var dateVerification: String = "",
    var idManagerVerification: String = "",
    var dateDeactivation: String = "",
    var defaultShipping: String = "",
    var defaultBilling: String = "",
    var fiasIDCountry: String = "",
    var fiasIDRegion: String = "",
    var fiasIDCity: String = "",
    var fiasIDArea: String = "",
    var fiasIDDistrict: String = "",
    var fiasIDStreet: String = "",
    var fiasIDHouse: String = "",
    var kladrCountry: String = "",
    var kladrRegion: String = "",
    var kladrCity: String = "",
    var kladrArea: String = "",
    var kladrDistrict: String = "",
    var kladrStreet: String = "",
    var kladrHouse: String = "",
    var nameCountry: String = "",
    var nameRegion: String = "",
    var nameCity: String = "",
    var nameStreet: String = "",
    var nameArea: String = "",
    var nameDistrict: String = "",
    var postalCode: String = "",
    var houseNUmber: String = "",
    var building: String = "",
    var apartment: String = "",
    var entrance: String = "",
    var floor: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    val isDefaultShippingAddress: Boolean = false,
    val isDefaultBillingAddress: Boolean = false
) {
    fun getFullAddress(): String {
        var address = ""

        nameStreet.let {
            address += "$it, "
        }

        houseNUmber.let {
            address += "д $it"
        }

        apartment.let {
            if (it != "0")
                address += ", кв $it"
        }

        floor.let {
            address += ", эт $it"
        }

        entrance.let {
            address += ", п $it "
        }
        return address
    }
}