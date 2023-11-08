package com.progressterra.ipbandroidview.shared

import com.google.gson.annotations.SerializedName

data class UserName(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("soname")
    val soname: String = "",

    @SerializedName("patronymic")
    val patronymic: String = ""
) : IsEmpty {

    override fun isEmpty(): Boolean = name == "" && soname == "" && patronymic == ""
}
