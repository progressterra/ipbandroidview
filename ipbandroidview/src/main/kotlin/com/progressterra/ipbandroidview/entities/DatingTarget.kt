package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatingTarget(
    override val id: String = "",
    val name: String = ""
) : Id, Parcelable, IsEmpty {

    override fun isEmpty() = this == DatingTarget()
}
