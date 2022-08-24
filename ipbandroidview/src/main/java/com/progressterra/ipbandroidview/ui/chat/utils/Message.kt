package com.progressterra.ipbandroidview.ui.chat.utils


import com.progressterra.ipbandroidapi.api.imessengercore.models.MessagesListResponse
import com.progressterra.ipbandroidapi.utils.extentions.format
import com.progressterra.ipbandroidapi.utils.extentions.orNow
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import java.util.*

data class Message(
    val idUnique: String,
    val contentText: String,
    val dateCreate: String,
    val time: String,
    val dateWoTime: String,
    val idClient: String,
    val rawDate: Date?
)

internal fun MessagesListResponse.convertToMessagesList(): List<Message> {
    return dataList
        ?.sortedByDescending { it.dateCreate }
        ?.map {
            Message(
                idUnique = it.idUnique ?: "",
                contentText = it.contentText ?: "",
                dateCreate = it.dateCreate.parseToDate().orNow().format("dd MMMM yyyy, H:mm"),
                time = it.dateCreate.parseToDate().orNow().format("HH:mm"),
                dateWoTime = it.dateCreate.parseToDate().orNow().format("dd MMMM yyyy"),
                idClient = it.idClient ?: "",
                it.dateCreate.parseToDate()
            )
        }
        ?: listOf()
}