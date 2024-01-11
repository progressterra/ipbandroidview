package com.progressterra.ipbandroidview.entities

import com.google.gson.annotations.SerializedName

data class Citizenship(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    override val id: String = ""
) : Id, IsEmpty {

    override fun isEmpty(): Boolean = id == "" && name == ""
}