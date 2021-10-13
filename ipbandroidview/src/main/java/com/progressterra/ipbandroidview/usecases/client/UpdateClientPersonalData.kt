package com.progressterra.ipbandroidview.usecases.client


import com.progressterra.ipbandroidapi.api.scrmApiQwerty.SCRMApiQwerty
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.PersonalRepository
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientInfoUI
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientUpdateInfo
import com.progressterra.ipbandroidview.utils.IUseCase
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.email
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult


internal class UpdateClientPersonalDataUseCase : IUpdateClientPersonalDataUseCase {
    private val repo: IRepository.Personal = PersonalRepository(
        SCRMApiQwerty.ClientCity(),
        SCRMApiQwerty.ClientsV3(),
        LoginApi.newInstance()
    )

    override suspend fun invoke(param: ClientUpdateInfo): SResult<ClientInfoUI> {
        val name = param.name
        val soname = param.soname
        val email = param.email

        if (name != UserData.clientInfo.name || soname != UserData.clientInfo.soname) {
            val nameResponse = repo.updatePersonalInfo(name, soname)
            if (nameResponse is SResult.Failed) return nameResponse.message.toFailedResult()
        }

        if (email != UserData.email) {
            val addEmail = repo.updateEmail(email)
            if (addEmail is SResult.Failed) return addEmail.message.toFailedResult()
            val confirmEmail = repo.confirmEmail(email)
            if (confirmEmail is SResult.Failed) return confirmEmail.message.toFailedResult()
        }


        return repo.updatePersonalInfoLocal()
    }
}

interface IUpdateClientPersonalDataUseCase :
    IUseCase.InOut<ClientUpdateInfo, SResult<ClientInfoUI>> {

    companion object {
        fun IUpdateClientPersonalDataUseCase(): IUpdateClientPersonalDataUseCase =
            UpdateClientPersonalDataUseCase()
    }
}