package com.progressterra.ipbandroidview.usecases.client

import com.progressterra.ipbandroidapi.api.scrmApiQwerty.SCRMApiQwerty
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.PersonalRepository
import com.progressterra.ipbandroidview.data.prefs.UserDataLocal
import com.progressterra.ipbandroidview.utils.IUseCase
import com.progressterra.ipbandroidview.utils.SResult


internal class GetClientCityUseCase : IGetClientCityUseCase {
    private val repo: IRepository.Personal = PersonalRepository(
        SCRMApiQwerty.ClientCity(),
        SCRMApiQwerty.ClientsV3(),
        LoginApi.newInstance()
    )

    override suspend fun invoke(): SResult<String> {
        val result = repo.getClientCity()
        if (result is SResult.Success) {
            UserDataLocal.city = result.data
        }

        return result
    }
}

interface IGetClientCityUseCase : IUseCase.Out<SResult<String>> {

    companion object {
        fun IGetClientCityUseCase(): IGetClientCityUseCase = GetClientCityUseCase()
    }
}