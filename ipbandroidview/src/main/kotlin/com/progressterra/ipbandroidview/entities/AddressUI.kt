package com.progressterra.ipbandroidview.entities

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidapi.api.scrm.models.RGAddressEntity
import com.progressterra.ipbandroidview.shared.IsEmpty

data class AddressUI(
    @SerializedName("idUnique")
    val idUnique: String = "",
    @SerializedName("defaultShipping")
    val defaultShipping: String = "",
    @SerializedName("defaultBilling")
    val defaultBilling: String = "",
    @SerializedName("fiasIDRegion")
    val fiasIDRegion: String = "",
    @SerializedName("fiasIDCity")
    val fiasIDCity: String = "",
    @SerializedName("fiasIDArea")
    val fiasIDArea: String = "",
    @SerializedName("fiasIDDistrict")
    val fiasIDDistrict: String = "",
    @SerializedName("fiasIDStreet")
    val fiasIDStreet: String = "",
    @SerializedName("fiasIDHouse")
    val fiasIDHouse: String = "",
    @SerializedName("kladrRegion")
    val kladrRegion: String = "",
    @SerializedName("kladrCity")
    val kladrCity: String = "",
    @SerializedName("kladrArea")
    val kladrArea: String = "",
    @SerializedName("kladrDistrict")
    val kladrDistrict: String = "",
    @SerializedName("kladrStreet")
    val kladrStreet: String = "",
    @SerializedName("kladrHouse")
    val kladrHouse: String = "",
    @SerializedName("nameCountry")
    val nameCountry: String = "",
    @SerializedName("nameRegion")
    val nameRegion: String = "",
    @SerializedName("nameCity")
    val nameCity: String = "",
    @SerializedName("nameStreet")
    val nameStreet: String = "",
    @SerializedName("nameArea")
    val nameArea: String = "",
    @SerializedName("nameDistrict")
    val nameDistrict: String = "",
    @SerializedName("postalCode")
    val postalCode: String = "",
    @SerializedName("houseNUmber")
    val houseNUmber: String = "",
    @SerializedName("building")
    val building: String = "",
    @SerializedName("apartment")
    val apartment: String = "",
    @SerializedName("entrance")
    val entrance: String = "",
    @SerializedName("floor")
    val floor: Int = 0,
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("longitude")
    val longitude: Double = 0.0
) : IsEmpty {

    fun convertAddressUiModelToDto() = RGAddressEntity(
        nameCity = nameCity,
        postalCode = postalCode,
        building = building,
        apartment = apartment.toIntOrNull() ?: 0,
        floor = floor,
        nameStreet = nameStreet,
        entrance = entrance.toIntOrNull() ?: 0,
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

    override fun isEmpty(): Boolean = idUnique == "" &&
        defaultShipping == "" &&
                defaultBilling == "" &&
                fiasIDRegion == "" &&
                fiasIDCity == "" &&
                fiasIDArea == "" &&
                fiasIDDistrict == "" &&
                fiasIDStreet == "" &&
                fiasIDHouse == "" &&
                kladrRegion == "" &&
                kladrCity == "" &&
                kladrArea == "" &&
                kladrDistrict == "" &&
                kladrStreet == "" &&
                kladrHouse == "" &&
                nameCountry == "" &&
                nameRegion == "" &&
                nameCity == "" &&
                nameStreet == "" &&
                nameArea == "" &&
                nameDistrict == "" &&
                postalCode == "" &&
                houseNUmber == "" &&
                building == "" &&
                apartment == "" &&
                entrance == "" &&
                floor == 0 &&
                latitude == 0.0 &&
                longitude == 0.0

    fun printAddress(): String = buildString {
        if (nameCity.isNotBlank())
            append(nameCity)
        if (nameStreet.isNotBlank())
            append(", ул. $nameStreet")
        if (houseNUmber.isNotBlank())
            append(", д. $houseNUmber")
        if (apartment.isNotBlank())
            append(", кв. $apartment")
    }
}