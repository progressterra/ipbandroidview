package com.progressterra.ipbandroidview.entities

data class Interest(
    override val id: String = "",
    val name: String = "",
    val picked: Boolean = false
) : Id
