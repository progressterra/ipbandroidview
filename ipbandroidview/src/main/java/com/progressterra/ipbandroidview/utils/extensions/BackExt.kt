package com.progressterra.ipbandroidview.utils.extensions

import com.progressterra.ipbandroidapi.api.scrm.models.clientinfo.ClientAdditionalInfoModel
import com.progressterra.ipbandroidapi.api.scrm.models.clientinfo.ClientInfoModel
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.ClientAdditionalInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.ClientInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remotedata.models.base.BaseResponse
import com.progressterra.ipbandroidapi.utils.extentions.orNow
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import com.progressterra.ipbandroidapi.utils.extentions.tryOrNull


fun BaseResponse.isSuccess(): Boolean {
    return this.status == 0 || this.result?.status == 0
}

val ClientInfoModel.toPrefModel: ClientInfo
    get() = ClientInfo(
        idUnique = idUnique,
        sex = when (sex) {
            1 -> SexType.MALE
            2 -> SexType.FEMALE
            else -> SexType.NONE
        },
        soname = soname ?: "",
        name = name ?: "",
        patronymic = patronymic ?: "",
        dateOfBirth = tryOrNull { dateOfBirth.parseToDate() }.orNow(),
        dateOfRegister = dateOfRegister
    )

val ClientAdditionalInfoModel.toPrefModel: ClientAdditionalInfo
    get() = ClientAdditionalInfo(
        additionalInfo = additionalInfo ?: "",
        statusCompletion = statusCompletion,
        emailGeneral = eMailGeneral ?: "",
        phoneGeneral = phoneGeneral ?: ""
    )