package com.progressterra.ipbandroidview.ext

import androidx.compose.ui.graphics.Color
import androidx.core.util.PatternsCompat
import com.progressterra.ipbandroidview.core.AttachedMedia
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Id
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.ChecklistStats
import java.time.LocalDate

fun String.isEmail(): Boolean = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun <T : Id> List<T>.replaceById(item: T): List<T> =
    this.toMutableList().apply { this[indexOfFirst { item.id == it.id }] = item }

fun <T> List<T>.removeItem(item: T): List<T> = this.toMutableList().apply { this.remove(item) }

fun <T : AttachedMedia<T>> List<T>.formPatch(): List<T> =
    this.filter { (it.local && !it.toRemove) || (!it.local && it.toRemove) }

fun <T : AttachedMedia<T>> List<T>.markToRemove(item: T): List<T> =
    this.toMutableList().apply { this[indexOfFirst { it.id == item.id }] = item.markToRemove() }

fun <T : AttachedMedia<T>> List<T>.markLastToRemove(): List<T> = this.toMutableList().apply {
    val temp = last().markToRemove()
    removeLast()
    add(temp)
}

fun List<Check>.createStats(): ChecklistStats {
    var successful = 0
    var failed = 0
    this.forEach {
        if (it.yesNo == true) successful++
        else if (it.yesNo == false) failed++
    }
    val total = this.size
    val remaining = total - successful - failed
    return ChecklistStats(
        total = total, successful = successful, failed = failed, remaining = remaining
    )
}

fun LocalDate.print(): String = "${this.dayOfMonth}.${this.monthValue}.${this.year}"

fun <T> Result<T>.throwOnFailure() {
    this.onFailure { throw it }
}

fun Boolean.toScreenState(): ScreenState = if (this) ScreenState.SUCCESS else ScreenState.ERROR

fun String.fromHexToColor(): Color = Color(android.graphics.Color.parseColor(this))