package com.progressterra.ipbandroidview.utils.extensions

import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.client_info.Client
import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.client_info.ClientAdditionalInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.ClientInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remoteData.models.base.BaseResponse
import com.progressterra.ipbandroidapi.utils.extentions.orNow
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import com.progressterra.ipbandroidapi.utils.extentions.tryOrNull


fun BaseResponse.isSuccess(): Boolean {
    return this.status == 0 || this.result?.status == 0
}

val Client.toPrefModel: ClientInfo
    get() = ClientInfo(
        idUnique = idUnique ?: "",
        sex = when (sex) {
            1 -> SexType.MALE
            2 -> SexType.FEMALE
            else -> SexType.NONE
        },
        soname = soname ?: "",
        name = name ?: "",
        patronymic = patronymic ?: "",
        dateOfBirth = tryOrNull { dateOfBirth.parseToDate() }.orNow(),
        dateOfRegister = dateOfRegister ?: ""
    )

val ClientAdditionalInfo.toPrefModel: com.progressterra.ipbandroidapi.localdata.shared_pref.models.ClientAdditionalInfo
    get() = com.progressterra.ipbandroidapi.localdata.shared_pref.models.ClientAdditionalInfo(
        additionalInfo = additionalInfo ?: "",
        statusCompletion = statusCompletion ?: 0,
        emailGeneral = eMailGeneral ?: "",
        phoneGeneral = phoneGeneral ?: ""
    )