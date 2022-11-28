package com.progressterra.ipbandroidview.model

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidview.core.IsEmpty

data class AddressUI(
    @SerializedName("idUnique")
    val idUnique: String = "",
    @SerializedName("idClient")
    val idClient: String = "",
    @SerializedName("dateAdded")
    val dateAdded: String = "",
    @SerializedName("dateVerification")
    val dateVerification: String = "",
    @SerializedName("idManagerVerification")
    val idManagerVerification: String = "",
    @SerializedName("dateDeactivation")
    val dateDeactivation: String = "",
    @SerializedName("defaultShipping")
    val defaultShipping: String = "",
    @SerializedName("defaultBilling")
    val defaultBilling: String = "",
    @SerializedName("fiasIDCountry")
    val fiasIDCountry: String = "",
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
    @SerializedName("kladrCountry")
    val kladrCountry: String = "",
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
    val floor: String = "",
    @SerializedName("latitude")
    val latitude: Double = 0.0,
    @SerializedName("longitude")
    val longitude: Double = 0.0
) : IsEmpty {

    override fun isEmpty(): Boolean = idUnique == "" &&
            idClient == "" &&
            dateAdded == "" &&
            dateVerification == "" &&
            idManagerVerification == "" &&
            dateDeactivation == "" &&
            defaultShipping == "" &&
            defaultBilling == "" &&
            fiasIDCountry == "" &&
            fiasIDRegion == "" &&
            fiasIDCity == "" &&
            fiasIDArea == "" &&
            fiasIDDistrict == "" &&
            fiasIDStreet == "" &&
            fiasIDHouse == "" &&
            kladrCountry == "" &&
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
            floor == "" &&
            latitude == 0.0 &&
            longitude == 0.0

    fun printAddress(): String = buildString {
        nameStreet?.let { append("$it, ") }
        houseNUmber?.let { append("д $it") }
        apartment?.let {
            if (it != "0")
                append(", кв $it")
        }
        floor?.let { append(", эт $it") }
        entrance?.let { append(", п $it ") }
    }
}