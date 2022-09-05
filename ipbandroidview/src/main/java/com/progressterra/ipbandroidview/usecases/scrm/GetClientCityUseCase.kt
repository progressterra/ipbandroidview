package com.progressterra.ipbandroidview.usecases.scrm

import com.progressterra.ipbandroidapi.api.city.CityRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository

interface GetClientCityUseCase {

    suspend fun getClientCity(): String

    class Base(
        private val cityRepository: CityRepository,
        private val sCRMRepository: SCRMRepository
    ) : GetClientCityUseCase {

        override suspend fun getClientCity(): String {
            sCRMRepository.getAccessToken().getOrNull()?.let { token ->
                cityRepository.getCity(token).getOrNull()?.let { info ->
                    return info.cityName
                }
            }
            return ""
        }
    }
}