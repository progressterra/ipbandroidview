package com.progressterra.ipbandroidview.usecases.client

import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.prefs.UserDataLocal
import com.progressterra.ipbandroidview.utils.IUseCase
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult


internal class GetClientCityUseCase : IGetClientCityUseCase {
//    private val repo: IRepository.Personal = IRepository.Personal(
//        SCRMApiQwerty.ClientCity(),
//        SCRMApiQwerty.ClientsV3(),
//        LoginApi.newInstance()
//    )

    override suspend fun invoke(): SResult<String> {
//        val result = repo.getClientCity()
//        if (result is SResult.Success) {
//            UserDataLocal.city = result.data
//        }

        return "".toSuccessResult()
    }
}

interface IGetClientCityUseCase : IUseCase.Out<SResult<String>> {

    companion object {
        fun IGetClientCityUseCase(): IGetClientCityUseCase = GetClientCityUseCase()
    }
}