package com.progressterra.ipbandroidview.ui.chat.utils


import com.progressterra.ipbandroidapi.api.iMessengerCore.models.MessagesListResponse
import com.progressterra.ipbandroidapi.utils.extentions.orNow
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import java.text.SimpleDateFormat
import java.util.*

data class Message(
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

fun Date.format(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(this)
}