package com.progressterra.ipbandroidview.ext

import androidx.core.util.PatternsCompat
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.core.AttachedMedia
import com.progressterra.ipbandroidview.ui.checklist.Check
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

fun <T : AttachedMedia<T>> List<T>.formPatch(): List<T> =
    this.filter { (it.local && !it.toRemove) || (!it.local && it.toRemove) }

fun <T : AttachedMedia<T>> List<T>.markToRemove(item: T): List<T> =
    this.filter { it.id != item.id }.toMutableList().apply { add(item.markToRemove()) }

fun <T : AttachedMedia<T>> List<T>.markLastToRemove(): List<T> =
    this.toMutableList().apply {
        val temp = last().markToRemove()
        removeLast()
        add(temp)
    }