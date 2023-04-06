package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidapi.api.message.models.RGMessages
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.DoubleMapper
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.entities.Message

interface MessageMapper : DoubleMapper<RGMessages, String, Message> {

    class Base(manageResources: ManageResources) : MessageMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RGMessages, data2: String) = Message(
            user = data1.idClient == data2,
            content = data1.contentText ?: noData,
            date = data1.dateCreate?.parseToDate()?.format("dd.MM.yyyy HH:mm") ?: noData
        )
    }
}