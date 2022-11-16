package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable

@Immutable
data class SimplePrice(val formattedPrice: String = "", val price: Int = 0)
