package com.progressterra.ipbandroidview.entities

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.scrm.models.RGAddressEntity
import com.progressterra.ipbandroidview.shared.IsEmpty

data class AddressUI(
    @SerializedName("idUnique")
    val idUnique: String? = null,
    @SerializedName("defaultShipping")
    val defaultShipping: String? = null,
    @SerializedName("defaultBilling")
    val defaultBilling: String? = null,
    @SerializedName("fiasIDRegion")
    val fiasIDRegion: String? = null,
    @SerializedName("fiasIDCity")
    val fiasIDCity: String? = null,
    @SerializedName("fiasIDArea")
    val fiasIDArea: String? = null,
    @SerializedName("fiasIDDistrict")
    val fiasIDDistrict: String? = null,
    @SerializedName("fiasIDStreet")
    val fiasIDStreet: String? = null,
    @SerializedName("fiasIDHouse")
    val fiasIDHouse: String? = null,
    @SerializedName("kladrRegion")
    val kladrRegion: String? = null,
    @SerializedName("kladrCity")
    val kladrCity: String? = null,
    @SerializedName("kladrArea")
    val kladrArea: String? = null,
    @SerializedName("kladrDistrict")
    val kladrDistrict: String? = null,
    @SerializedName("kladrStreet")
    val kladrStreet: String? = null,
    @SerializedName("kladrHouse")
    val kladrHouse: String? = null,
    @SerializedName("nameCountry")
    val nameCountry: String? = null,
    @SerializedName("nameRegion")
    val nameRegion: String? = null,
    @SerializedName("nameCity")
    val nameCity: String? = null,
    @SerializedName("nameStreet")
    val nameStreet: String? = null,
    @SerializedName("nameArea")
    val nameArea: String? = null,
    @SerializedName("nameDistrict")
    val nameDistrict: String? = null,
    @SerializedName("postalCode")
    val postalCode: String? = null,
    @SerializedName("houseNUmber")
    val houseNUmber: String? = null,
    @SerializedName("building")
    val building: String? = null,
    @SerializedName("apartment")
    val apartment: String? = null,
    @SerializedName("entrance")
    val entrance: String? = null,
    @SerializedName("floor")
    val floor: Int? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null
) : IsEmpty {

    fun convertAddressUiModelToDto() = RGAddressEntity(
        nameCity = nameCity,
        postalCode = postalCode,
        building = building,
        apartment = apartment?.toIntOrNull(),
        floor = floor,
        nameStreet = nameStreet,
        entrance = entrance?.toIntOrNull(),
        idClient = null,
        dateVerification = null,
        idManagerVerification = null,
        dateDeactivation = null,
        defaultBilling = defaultBilling,
        defaultShipping = defaultShipping,
        fiasIDCountry = null,
        fiasIDRegion = fiasIDRegion,
        fiasIDCity = fiasIDCity,
        fiasIDArea = fiasIDArea,
        fiasIDDistrict = fiasIDDistrict,
        fiasIDHouse = fiasIDHouse,
        fiasIDStreet = fiasIDStreet,
        kladrCountry = null,
        kladrRegion = kladrRegion,
        kladrCity = kladrCity,
        kladrArea = kladrArea,
        kladrDistrict = kladrArea,
        kladrStreet = kladrStreet,
        kladrHouse = kladrHouse,
        nameCountry = nameCountry,
        nameRegion = nameRegion,
        nameArea = nameArea,
        nameDistrict = nameDistrict,
        houseNUmber = houseNUmber,
        latitude = latitude,
        longitude = longitude
    )

    override fun isEmpty(): Boolean = this == AddressUI()

    fun printAddress(): String = buildString {
        if (nameCity?.isNotBlank() == true)
            append(nameCity)
        if (nameStreet?.isNotBlank() == true)
            append(", ул. $nameStreet")
        if (houseNUmber?.isNotBlank() == true)
            append(", д. $houseNUmber")
        if (apartment?.isNotBlank() == true)
            append(", кв. $apartment")
    }
}