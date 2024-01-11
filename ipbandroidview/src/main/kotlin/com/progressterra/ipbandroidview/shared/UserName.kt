package com.progressterra.ipbandroidview.shared

import com.google.gson.annotations.SerializedName
import com.progressterra.ipbandroidview.entities.IsEmpty

/**
 * Data class used to store user name, soname and patronymic
 */
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
