package com.progressterra.ipbandroidview.model

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidview.core.IsEmpty

data class AddressUI(
    @SerializedName("idUnique")
    val idUnique: String? = null,
    @SerializedName("idClient")
    val idClient: String? = null,
    @SerializedName("dateAdded")
    val dateAdded: String? = null,
    @SerializedName("dateVerification")
    val dateVerification: String? = null,
    @SerializedName("idManagerVerification")
    val idManagerVerification: String? = null,
    @SerializedName("dateDeactivation")
    val dateDeactivation: String? = null,
    @SerializedName("defaultShipping")
    val defaultShipping: String? = null,
    @SerializedName("defaultBilling")
    val defaultBilling: String? = null,
    @SerializedName("fiasIDCountry")
    val fiasIDCountry: String? = null,
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
    @SerializedName("kladrCountry")
    val kladrCountry: String? = null,
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
    val floor: String? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null
) : IsEmpty {

    override fun isEmpty(): Boolean = idUnique == null &&
            idClient == null &&
            dateAdded == null &&
            dateVerification == null &&
            idManagerVerification == null &&
            dateDeactivation == null &&
            defaultShipping == null &&
            defaultBilling == null &&
            fiasIDCountry == null &&
            fiasIDRegion == null &&
            fiasIDCity == null &&
            fiasIDArea == null &&
            fiasIDDistrict == null &&
            fiasIDStreet == null &&
            fiasIDHouse == null &&
            kladrCountry == null &&
            kladrRegion == null &&
            kladrCity == null &&
            kladrArea == null &&
            kladrDistrict == null &&
            kladrStreet == null &&
            kladrHouse == null &&
            nameCountry == null &&
            nameRegion == null &&
            nameCity == null &&
            nameStreet == null &&
            nameArea == null &&
            nameDistrict == null &&
            postalCode == null &&
            houseNUmber == null &&
            building == null &&
            apartment == null &&
            entrance == null &&
            floor == null &&
            latitude == null &&
            longitude == null


    override fun toString(): String = buildString {
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