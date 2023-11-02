package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.iamhere.models.EnumTypeStatusConnect
import com.progressterra.ipbandroidview.shared.IsEmpty
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Connection(
    override val id: String = "",
    val own: Boolean = false,
    val user: DatingUser = DatingUser(),
    val type: EnumTypeStatusConnect = EnumTypeStatusConnect.WAIT
) : Id, Parcelable, IsEmpty {

    override fun isEmpty() = this == Connection()
}
