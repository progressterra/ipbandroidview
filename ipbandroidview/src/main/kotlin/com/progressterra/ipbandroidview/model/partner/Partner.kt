package com.progressterra.ipbandroidview.model.partner

import com.progressterra.ipbandroidview.model.Id

data class Partner(
    override val id: String = "",
    val title: String = "",
    val description: String = "",
    val offerList: List<Offer> = emptyList(),
    val webSite: String = "",
    val phone: String = "",
    val headImageUrl: String = "",
    val logoImageUrl: String = "",
    val miniImageUrl: String = ""
) : Id