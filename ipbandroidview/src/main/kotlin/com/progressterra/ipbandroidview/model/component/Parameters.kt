package com.progressterra.ipbandroidview.model.component

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.GoodsParameters

@Immutable
interface Parameters {

    val parameters: List<GoodsParameters>
}