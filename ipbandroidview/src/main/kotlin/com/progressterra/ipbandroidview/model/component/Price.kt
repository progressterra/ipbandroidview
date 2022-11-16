package com.progressterra.ipbandroidview.model.component

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.SimplePrice

@Immutable
interface Price {

    val price: SimplePrice
}