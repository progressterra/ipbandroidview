package com.progressterra.ipbandroidview.entities

import com.progressterra.ipbandroidview.shared.IsEmpty

data class Citizenship(
    val name: String = "",
    override val id: String = ""
) : Id, IsEmpty {

    override fun isEmpty(): Boolean = this == Citizenship()
}