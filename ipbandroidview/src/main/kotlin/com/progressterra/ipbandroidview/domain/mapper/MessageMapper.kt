package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.message.models.RGMessages
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.model.Message

interface MessageMapper {

    fun map(data: RGMessages, userId: String): Message

    class Base(manageResources: ManageResources) : MessageMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RGMessages, userId: String) = Message(
            user = data.idClient == userId,
            content = data.contentText ?: noData,
            date = data.dateCreate?.parseToDate()?.format("dd.MM.yyyy HH:mm") ?: noData
        )
    }
}