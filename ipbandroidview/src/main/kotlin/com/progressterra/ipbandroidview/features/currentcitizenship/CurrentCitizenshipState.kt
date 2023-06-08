package com.progressterra.ipbandroidview.features.currentcitizenship

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Citizenship

@Immutable
data class CurrentCitizenshipState(
    val citizenship: Citizenship? = null
)