package com.progressterra.ipbandroidview.utils.extensions

import com.progressterra.ipbandroidapi.api.scrm.model.ClientAdditionalInfoModel
import com.progressterra.ipbandroidapi.api.scrm.model.ClientInfoModel
import com.progressterra.ipbandroidapi.base.BaseResponse
import com.progressterra.ipbandroidapi.user.ClientAdditionalInfo
import com.progressterra.ipbandroidapi.user.ClientInfo
import com.progressterra.ipbandroidapi.user.SexType
import com.progressterra.ipbandroidapi.utils.orNow
import com.progressterra.ipbandroidapi.utils.parseToDate
import com.progressterra.ipbandroidapi.utils.tryOrNull

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