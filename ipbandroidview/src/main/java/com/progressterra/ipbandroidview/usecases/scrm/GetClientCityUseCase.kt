package com.progressterra.ipbandroidview.usecases.scrm

import com.progressterra.ipbandroidapi.api.city.CityRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository

interface GetClientCityUseCase {

    suspend fun getClientCity(): Result<String>

    class Base(
        private val cityRepository: CityRepository,
        private val sCRMRepository: SCRMRepository
    ) : GetClientCityUseCase {

        override suspend fun getClientCity(): Result<String> {
            val tokenResult = sCRMRepository.getAccessToken()
            if (tokenResult.isFailure)
                return Result.failure(tokenResult.exceptionOrNull()!!)
            val cityResponse = cityRepository.getCity(tokenResult.getOrNull()!!)
            if (cityResponse.isFailure)
                return Result.failure(cityResponse.exceptionOrNull()!!)
            return Result.success(cityResponse.getOrNull()!!.cityName)
        }
    }
}