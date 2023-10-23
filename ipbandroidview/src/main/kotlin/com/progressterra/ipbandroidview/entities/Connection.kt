package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.iamhere.models.EnumTypeStatusConnect

@Immutable
data class Connection(
    override val id: String = "",
    val user: DatingUser = DatingUser(),
    val type: EnumTypeStatusConnect = EnumTypeStatusConnect.WAIT
) : Id
