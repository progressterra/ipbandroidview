package com.progressterra.ipbandroidview.processes.mapper

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidview.core.AbstractMapper
import com.progressterra.ipbandroidview.core.Mapper

interface ImageMapper : Mapper<String, String> {

    class Base(
        gson: Gson
    ) : ImageMapper, AbstractMapper(gson) {

        override fun map(data: String): String = gson.fromJson(
            data, ImageData::class.java
        ).list.first().url

        data class ImageData(
            @SerializedName("datalist") val list: List<Item>
        ) {

            data class Item(
                @SerializedName("URL") val url: String
            )
        }
    }
}