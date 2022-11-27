package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.core.IsEmpty

data class AddressUI(
    val idUnique: String? = null,
    val idClient: String? = null,
    val dateAdded: String? = null,
    val dateVerification: String? = null,
    val idManagerVerification: String? = null,
    val dateDeactivation: String? = null,
    val defaultShipping: String? = null,
    val defaultBilling: String? = null,
    val fiasIDCountry: String? = null,
    val fiasIDRegion: String? = null,
    val fiasIDCity: String? = null,
    val fiasIDArea: String? = null,
    val fiasIDDistrict: String? = null,
    val fiasIDStreet: String? = null,
    val fiasIDHouse: String? = null,
    val kladrCountry: String? = null,
    val kladrRegion: String? = null,
    val kladrCity: String? = null,
    val kladrArea: String? = null,
    val kladrDistrict: String? = null,
    val kladrStreet: String? = null,
    val kladrHouse: String? = null,
    val nameCountry: String? = null,
    val nameRegion: String? = null,
    val nameCity: String? = null,
    val nameStreet: String? = null,
    val nameArea: String? = null,
    val nameDistrict: String? = null,
    val postalCode: String? = null,
    val houseNUmber: String? = null,
    val building: String? = null,
    val apartment: String? = null,
    val entrance: String? = null,
    val floor: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isDefaultShippingAddress: Boolean? = null,
    val isDefaultBillingAddress: Boolean? = null
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
            longitude == null &&
            isDefaultShippingAddress == null &&
            isDefaultBillingAddress == null


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