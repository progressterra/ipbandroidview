package com.progressterra.ipbandroidview.ui.checklist

import android.os.Parcelable
import com.progressterra.ipbandroidview.model.Id
import kotlinx.parcelize.Parcelize

@Parcelize
data class Check(
    override val id: String,
    val name: String,
    val description: String,
    val category: String,
    val categoryNumber: Int,
    val ordinal: Int,
    val yesNo: Boolean?,
    val comment: String
) : Parcelable, Id {

    fun printCategory(): String = "$categoryNumber. $category"

    fun printTitle(): String = "$categoryNumber-$ordinal"
}