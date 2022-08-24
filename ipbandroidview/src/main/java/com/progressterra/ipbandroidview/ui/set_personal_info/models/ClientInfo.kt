package com.progressterra.ipbandroidview.ui.set_personal_info.models

import com.progressterra.ipbandroidapi.api.scrm.models.clientinfo.ClientInfoResponse
import java.util.*

data class ClientInfo(
    val name: String,
    val soname: String,
    val fullName: String,
    var patronymic: String,
    var dateOfBirth: String
) {
    companion object {
        fun convertToUiModel(response: ClientInfoResponse): ClientInfo {
            return ClientInfo(
                response.data.clientInfo.name ?: "",
                response.data.clientInfo.soname ?: "",
                "${
                    response.data.clientInfo.name?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    } ?: ""
                } ${
                    response.data.clientInfo.soname?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    } ?: ""
                }",
                response.data.clientInfo.patronymic ?: "",
                response.data.clientInfo.dateOfBirth ?: ""
            )
        }
    }
}