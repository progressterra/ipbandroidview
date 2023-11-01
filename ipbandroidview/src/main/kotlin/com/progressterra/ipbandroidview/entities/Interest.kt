package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Interest(
    override val id: String = "",
    val name: String = "",
    val picked: Boolean = false
) : Id, Parcelable, IsEmpty {

    override fun isEmpty() = this == Interest()
}
