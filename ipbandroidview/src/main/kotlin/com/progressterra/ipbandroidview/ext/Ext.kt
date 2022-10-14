package com.progressterra.ipbandroidview.ext

import androidx.core.util.PatternsCompat
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.ui.checklist.Check

fun String.isEmail(): Boolean = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun Boolean?.toYesNo(): YesNo = when (this) {
    true -> YesNo.YES
    false -> YesNo.NO
    null -> YesNo.NONE
}

fun YesNo.toBoolean(): Boolean? = when (this) {
    YesNo.NONE -> null
    YesNo.YES -> true
    YesNo.NO -> false
}

fun List<Check>.replaceById(check: Check): List<Check> =
    this.filter { it.id != check.id }.toMutableList().apply { add(check) }