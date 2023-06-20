package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.convertAddressUiModelToDto
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface SaveAddressUseCase {

    suspend operator fun invoke(
        city: String,
        home: String,
        entrance: String,
        apartment: String
    ): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val addressRepository: AddressRepository
    ) : SaveAddressUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            city: String,
            home: String,
            entrance: String,
            apartment: String
        ): Result<Unit> = withToken { token ->
            val address = AddressUI(
                idUnique = "00000000-0000-0000-0000-000000000000",
                idClient = "00000000-0000-0000-0000-000000000000",
                dateAdded = "0001-01-01T00:00:00",
                dateVerification = "",
                idManagerVerification = "",
                dateDeactivation = "",
                defaultShipping = "",
                defaultBilling = "",
                fiasIDCountry = "",
                fiasIDRegion = "",
                fiasIDCity = "",
                fiasIDArea = "",
                fiasIDDistrict = "",
                fiasIDStreet = "",
                fiasIDHouse = "",
                kladrHouse = "",
                kladrCountry = "00000000-0000-0000-0000-000000000000",
                kladrRegion = "",
                kladrCity = "",
                kladrArea = "",
                kladrDistrict = "",
                kladrStreet = "",
                nameCountry = "",
                nameRegion = "",
                nameCity = city,
                nameStreet = "",
                nameArea = "",
                nameDistrict = "",
                postalCode = "",
                houseNUmber = home,
                building = "",
                apartment = apartment.toInt(),
                entrance = entrance.toInt(),
                floor = 0,
                latitude = 0.0,
                longitude = 0.0
            )
            addressRepository.uploadClientAddress(
                accessToken = token,
                request = address.convertAddressUiModelToDto()
            ).throwOnFailure()
            UserData.address = address
        }
    }
}
