package com.progressterra.ipbandroidview.entities


data class Message(
    override val id: String,
    val user: Boolean,
    val content: String,
    val date: String
) : Id