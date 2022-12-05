package com.progressterra.ipbandroidview.data

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidview.core.IsEmpty

data class UserName(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("surname")
    val surname: String = "",

    @SerializedName("patronymic")
    val patronymic: String = ""
) : IsEmpty {

    override fun isEmpty(): Boolean = name == "" && surname == "" && patronymic == ""
}
