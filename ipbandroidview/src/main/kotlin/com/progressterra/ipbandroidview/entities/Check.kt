package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Check(
    override val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val categoryNumber: Int = 0,
    val ordinal: Int = 0,
    val yesNo: Boolean? = null,
    val comment: String = ""
) : Parcelable, Id {

    fun printCategory(): String = "$categoryNumber. $category"

    fun printTitle(): String = "$categoryNumber-$ordinal"

    fun updateComment(comment: String) = copy(comment = comment)

    fun updateYesNo(yesNo: Boolean) = copy(yesNo = yesNo)
}