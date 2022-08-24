package com.progressterra.ipbandroidview.ui.addresses.models

import com.progressterra.ipbandroidapi.api.scrm.models.address.Address
import com.progressterra.ipbandroidapi.api.scrm.models.address.ListOfAddressesResponse
import com.progressterra.ipbandroidapi.api.scrm.models.address.dadata.Suggestion
import com.progressterra.ipbandroidapi.api.scrm.models.address.dadata.SuggestionExtendedInfo

object AddressesMapper {
    fun convertSuggestionToAddressUIModel(suggestion: SuggestionExtendedInfo): AddressUI {
        return AddressUI(
            idUnique = "00000000-0000-0000-0000-000000000000",
            idClient = "00000000-0000-0000-0000-000000000000",
            dateAdded = "0001-01-01T00:00:00",
            dateVerification = null,
            idManagerVerification = null,
            dateDeactivation = null,
            defaultShipping = null,
            defaultBilling = null,
            fiasIDCountry = null,
            fiasIDRegion = suggestion.regionFiasId,
            fiasIDCity = suggestion.cityFiasId,
            fiasIDArea = suggestion.areaFiasId,
            fiasIDDistrict = suggestion.cityDistrictFiasId,
            fiasIDStreet = suggestion.streetFiasId,
            fiasIDHouse = suggestion.houseFiasId,
            kladrHouse = suggestion.houseKladrId,
            kladrCountry = "00000000-0000-0000-0000-000000000000",
            kladrRegion = suggestion.regionKladrId,
            kladrCity = suggestion.cityKladrId,
            kladrArea = suggestion.areaKladrId,
            kladrDistrict = suggestion.cityDistrictKladrId,
            kladrStreet = suggestion.streetKladrId,
            nameCountry = suggestion.country,
            nameRegion = suggestion.region,
            nameCity = suggestion.city,
            nameStreet = suggestion.street,
            nameArea = suggestion.area,
            nameDistrict = suggestion.cityDistrict,
            postalCode = suggestion.postalCode,
            houseNUmber = suggestion.house,
            building = suggestion.block,
            apartment = suggestion.flat,
            entrance = suggestion.entrance,
            floor = suggestion.floor,
            latitude = try {
                suggestion.geoLat?.toDouble() ?: 0.0
            } catch (e: Exception) {
                0.0
            },
            longitude = try {
                suggestion.geoLon?.toDouble() ?: 0.0
            } catch (e: Exception) {
                0.0
            },
            isDefaultShippingAddress = null,
            isDefaultBillingAddress = null
        )
    }

    fun convertSuggestionsDtoToUIModels(suggestions: List<Suggestion>?): List<SuggestionUI> {
        return suggestions?.map {
            SuggestionUI(
                suggestionExtendedInfo = it.suggestionExtendedInfo,
                previewOfSuggestion = it.previewOfSuggestion
            )
        } ?: emptyList()
    }

    fun convertAddressUiModelToDto(addressUI: AddressUI): Address {
        return Address(
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
    }

    fun convertDtoToAddressUiModel(listOfAddressesResponse: ListOfAddressesResponse?): List<AddressUI> {
        val defaultShippingAddressId =
            listOfAddressesResponse?.addressInfo?.defaultShippingAddress?.idUnique
        val defaultBillingAddressId =
            listOfAddressesResponse?.addressInfo?.defaultBillingAddress?.idUnique

        return listOfAddressesResponse?.addressInfo?.listAddress?.map {
            AddressUI(
                idUnique = it.idUnique,
                nameCity = it.nameCity,
                postalCode = it.postalCode,
                building = it.building,
                apartment = it.apartment,
                floor = it.floor,
                nameStreet = it.nameStreet,
                entrance = it.entrance,
                isDefaultShippingAddress = it.idUnique == defaultShippingAddressId,
                isDefaultBillingAddress = it.idUnique == defaultBillingAddressId,
                idClient = it.idClient,
                dateAdded = it.dateAdded,
                dateVerification = it.dateVerification,
                idManagerVerification = it.idManagerVerification,
                dateDeactivation = it.dateDeactivation,
                defaultBilling = it.defaultBilling,
                defaultShipping = it.defaultShipping,
                fiasIDCountry = it.fiasIDCountry,
                fiasIDRegion = it.fiasIDRegion,
                fiasIDCity = it.fiasIDCity,
                fiasIDArea = it.fiasIDArea,
                fiasIDDistrict = it.fiasIDDistrict,
                fiasIDHouse = it.fiasIDHouse,
                fiasIDStreet = it.fiasIDStreet,
                kladrCountry = it.kladrCountry,
                kladrRegion = it.kladrRegion,
                kladrCity = it.kladrCity,
                kladrArea = it.kladrArea,
                kladrDistrict = it.kladrArea,
                kladrStreet = it.kladrStreet,
                kladrHouse = it.kladrHouse,
                nameCountry = it.nameCountry,
                nameRegion = it.nameRegion,
                nameArea = it.nameArea,
                nameDistrict = it.nameDistrict,
                houseNUmber = it.houseNUmber,
                latitude = it.latitude,
                longitude = it.longitude
            )
        } ?: emptyList()
    }
}