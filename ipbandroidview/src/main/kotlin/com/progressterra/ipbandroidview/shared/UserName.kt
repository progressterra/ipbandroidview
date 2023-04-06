package com.progressterra.ipbandroidview.shared

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidview.shared.IsEmpty

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
