package com.progressterra.ipbandroidview.ui.feeds.models

class FeedUi(
    val alias: String?,
    val contentType: ContentType,
    val dateEvent: String?,
    val idUnique: String?,
    val previewText: String?,
    val urlData: String?
)

enum class ContentType {
    HTML,
    VIDEO
}