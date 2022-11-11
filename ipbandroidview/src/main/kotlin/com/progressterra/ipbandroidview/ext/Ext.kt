package com.progressterra.ipbandroidview.ext

import androidx.core.util.PatternsCompat
import com.progressterra.ipbandroidview.components.yesno.YesNo
import com.progressterra.ipbandroidview.core.AttachedMedia
import com.progressterra.ipbandroidview.model.component.Id
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

fun <T : Id> List<T>.replaceById(item: T): List<T> =
    this.toMutableList().apply { this[indexOfFirst { item.id == it.id }] = item }

fun <T> List<T>.removeItem(item: T): List<T> =
    this.toMutableList().apply { this.remove(item) }

fun <T : AttachedMedia<T>> List<T>.formPatch(): List<T> =
    this.filter { (it.local && !it.toRemove) || (!it.local && it.toRemove) }

fun <T : AttachedMedia<T>> List<T>.markToRemove(item: T): List<T> =
    this.toMutableList().apply { this[indexOfFirst { it.id != item.id }] = item.markToRemove() }

fun <T : AttachedMedia<T>> List<T>.markLastToRemove(): List<T> = this.toMutableList().apply {
    val temp = last().markToRemove()
    removeLast()
    add(temp)
}