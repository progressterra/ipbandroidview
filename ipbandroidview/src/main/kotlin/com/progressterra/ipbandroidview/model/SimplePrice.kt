package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidview.core.IsEmpty

data class SimplePrice(val formattedPrice: String = "", val price: Int = 0) : IsEmpty {

    override fun isEmpty(): Boolean = formattedPrice == "" && price == 0
}
