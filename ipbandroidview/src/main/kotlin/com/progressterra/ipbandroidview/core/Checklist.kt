package com.progressterra.ipbandroidview.core

import android.os.Parcelable
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.composable.yesno.YesNo
import com.progressterra.ipbandroidview.ui.checklist.Check
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checklist(
    val checklistId: String,
    val placeId: String,
    val documentId: String?,
    val name: String,
    val done: Boolean,
    val ongoing: Boolean,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val checks: List<Check>
) : Parcelable {

    fun createStats(): ChecklistStats {
        var successful = 0
        var failed = 0
        checks.forEach {
            if (it.yesNo == YesNo.YES)
                successful++
            else if (it.yesNo == YesNo.NO)
                failed++
        }
        val total = checks.size
        val remaining = total - successful - failed
        return ChecklistStats(
            total = total, successful = successful, failed = failed,
            remaining = remaining
        )
    }
}
