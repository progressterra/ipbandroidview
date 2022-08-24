package com.progressterra.ipbandroidview.ui.media_data.models

import com.progressterra.ipbandroidapi.api.ipbmediadatacore.models.MediaData

data class MediaDataUi(
    val alias: String?,
    val contentType: ContentType,
    val dateEvent: String?,
    val idUnique: String?,
    val previewText: String?,
    val urlData: String?
)

fun List<MediaData>.toUiModel(): List<MediaDataUi> {
    return this.map {
        it.toUiModel()
    }
}

fun MediaData.toUiModel(): MediaDataUi {
    return MediaDataUi(
        alias = this.alias,
        contentType = when (this.contentType) {
            1 -> ContentType.VIDEO
            2 -> ContentType.PDF
            3 -> ContentType.HTML
            else -> ContentType.UNKNOWN
        },
        dateEvent = this.dateEvent,
        idUnique = this.idUnique,
        previewText = this.previewText,
        urlData = this.urlData
    )
}

enum class ContentType {
    HTML,
    VIDEO,
    PDF,
    UNKNOWN
}