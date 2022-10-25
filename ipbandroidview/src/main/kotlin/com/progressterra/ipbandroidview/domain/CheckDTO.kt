package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidview.composable.yesno.YesNo
import java.util.*

data class CheckDTO(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val categoryNumber: Int,
    val dateAdded: Date,
    val yesNo: YesNo,
    val comment: String
)
