package com.progressterra.ipbandroidview.ui.set_personal_info.models

import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.client_info.ClientInfoResponse
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
                response.client?.name ?: "",
                response.client?.soname ?: "",
                "${
                    response.client?.name?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    } ?: ""
                } ${
                    response.client?.soname?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    } ?: ""
                }",
                response.client?.patronymic ?: "",
                response.client?.dateOfBirth ?: ""
            )
        }
    }
}