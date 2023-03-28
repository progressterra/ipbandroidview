package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidapi.api.address.models.DataAddress
import com.progressterra.ipbandroidapi.api.address.models.RGAddress
import com.progressterra.ipbandroidapi.api.suggestion.model.Suggestion
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.SuggestionUI

interface AddressesMapper {

    fun convertSuggestionToAddressUIModel(suggestion: SuggestionExtendedInfo): AddressUI

    fun convertDtoToAddressUiModel(listOfAddressesResponse: DataAddress?): List<AddressUI>

    fun convertSuggestionsDtoToUIModels(suggestions: List<Suggestion>?): List<SuggestionUI>

    fun convertAddressUiModelToDto(addressUI: AddressUI): RGAddress

    class Base : AddressesMapper {

        override fun convertSuggestionToAddressUIModel(suggestion: SuggestionExtendedInfo): AddressUI =
            AddressUI(
                idUnique = "00000000-0000-0000-0000-000000000000",
                idClient = "00000000-0000-0000-0000-000000000000",
                dateAdded = "0001-01-01T00:00:00",
                dateVerification = "",
                idManagerVerification = "",
                dateDeactivation = "",
                defaultShipping = "",
                defaultBilling = "",
                fiasIDCountry = "",
                fiasIDRegion = suggestion.regionFiasId ?: "",
                fiasIDCity = suggestion.cityFiasId ?: "",
                fiasIDArea = suggestion.areaFiasId ?: "",
                fiasIDDistrict = suggestion.cityDistrictFiasId ?: "",
                fiasIDStreet = suggestion.streetFiasId ?: "",
                fiasIDHouse = suggestion.houseFiasId ?: "",
                kladrHouse = suggestion.houseKladrId ?: "",
                kladrCountry = "00000000-0000-0000-0000-000000000000",
                kladrRegion = suggestion.regionKladrId ?: "",
                kladrCity = suggestion.cityKladrId ?: "",
                kladrArea = suggestion.areaKladrId ?: "",
                kladrDistrict = suggestion.cityDistrictKladrId ?: "",
                kladrStreet = suggestion.streetKladrId ?: "",
                nameCountry = suggestion.country ?: "",
                nameRegion = suggestion.region ?: "",
                nameCity = suggestion.city ?: "",
                nameStreet = suggestion.street ?: "",
                nameArea = suggestion.area ?: "",
                nameDistrict = suggestion.cityDistrict ?: "",
                postalCode = suggestion.postalCode ?: "",
                houseNUmber = suggestion.house ?: "",
                building = suggestion.block ?: "",
                apartment = suggestion.flat?.toInt() ?: 0,
                entrance = suggestion.entrance?.toInt() ?: 0,
                floor = suggestion.floor?.toInt() ?: 0,
                latitude = suggestion.geoLat?.toDouble() ?: 0.0,
                longitude = suggestion.geoLon?.toDouble() ?: 0.0
            )

        override fun convertSuggestionsDtoToUIModels(
            suggestions: List<Suggestion>?
        ): List<SuggestionUI> = suggestions?.map {
            SuggestionUI(
                suggestionExtendedInfo = it.suggestionExtendedInfo ?: SuggestionExtendedInfo(),
                previewOfSuggestion = it.previewOfSuggestion ?: ""
            )
        } ?: emptyList()

        override fun convertAddressUiModelToDto(addressUI: AddressUI): RGAddress = RGAddress(
            idUnique = addressUI.idUnique,
            nameCity = addressUI.nameCity,
            postalCode = addressUI.postalCode,
            building = addressUI.building,
            apartment = addressUI.apartment,
            floor = addressUI.floor,
            nameStreet = addressUI.nameStreet,
            entrance = addressUI.entrance,
            idClient = addressUI.idClient,
            dateAdded = addressUI.dateAdded,
            dateVerification = addressUI.dateVerification,
            idManagerVerification = addressUI.idManagerVerification,
            dateDeactivation = addressUI.dateDeactivation,
            defaultBilling = addressUI.defaultBilling,
            defaultShipping = addressUI.defaultShipping,
            fiasIDCountry = addressUI.fiasIDCountry,
            fiasIDRegion = addressUI.fiasIDRegion,
            fiasIDCity = addressUI.fiasIDCity,
            fiasIDArea = addressUI.fiasIDArea,
            fiasIDDistrict = addressUI.fiasIDDistrict,
            fiasIDHouse = addressUI.fiasIDHouse,
            fiasIDStreet = addressUI.fiasIDStreet,
            kladrCountry = addressUI.kladrCountry,
            kladrRegion = addressUI.kladrRegion,
            kladrCity = addressUI.kladrCity,
            kladrArea = addressUI.kladrArea,
            kladrDistrict = addressUI.kladrArea,
            kladrStreet = addressUI.kladrStreet,
            kladrHouse = addressUI.kladrHouse,
            nameCountry = addressUI.nameCountry,
            nameRegion = addressUI.nameRegion,
            nameArea = addressUI.nameArea,
            nameDistrict = addressUI.nameDistrict,
            houseNUmber = addressUI.houseNUmber,
            latitude = addressUI.latitude,
            longitude = addressUI.longitude
        )

        override fun convertDtoToAddressUiModel(
            listOfAddressesResponse: DataAddress?
        ): List<AddressUI> = listOfAddressesResponse?.listAddAddress?.map {
            AddressUI(
                idUnique = it.idUnique ?: "",
                nameCity = it.nameCity ?: "",
                postalCode = it.postalCode ?: "",
                building = it.building ?: "",
                apartment = it.apartment ?: 0,
                floor = it.floor ?: 0,
                nameStreet = it.nameStreet ?: "",
                entrance = it.entrance ?: 0,
                idClient = it.idClient ?: "",
                dateAdded = it.dateAdded ?: "",
                dateVerification = it.dateVerification ?: "",
                idManagerVerification = it.idManagerVerification ?: "",
                dateDeactivation = it.dateDeactivation ?: "",
                defaultBilling = it.defaultBilling ?: "",
                defaultShipping = it.defaultShipping ?: "",
                fiasIDCountry = it.fiasIDCountry ?: "",
                fiasIDRegion = it.fiasIDRegion ?: "",
                fiasIDCity = it.fiasIDCity ?: "",
                fiasIDArea = it.fiasIDArea ?: "",
                fiasIDDistrict = it.fiasIDDistrict ?: "",
                fiasIDHouse = it.fiasIDHouse ?: "",
                fiasIDStreet = it.fiasIDStreet ?: "",
                kladrCountry = it.kladrCountry ?: "",
                kladrRegion = it.kladrRegion ?: "",
                kladrCity = it.kladrCity ?: "",
                kladrArea = it.kladrArea ?: "",
                kladrDistrict = it.kladrArea ?: "",
                kladrStreet = it.kladrStreet ?: "",
                kladrHouse = it.kladrHouse ?: "",
                nameCountry = it.nameCountry ?: "",
                nameRegion = it.nameRegion ?: "",
                nameArea = it.nameArea ?: "",
                nameDistrict = it.nameDistrict ?: "",
                houseNUmber = it.houseNUmber ?: "",
                latitude = it.latitude ?: 0.0,
                longitude = it.longitude ?: 0.0
            )
        } ?: emptyList()
    }
}