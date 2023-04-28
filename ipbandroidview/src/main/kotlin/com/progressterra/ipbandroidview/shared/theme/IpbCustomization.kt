package com.progressterra.ipbandroidview.shared.theme

data class IpbCustomization(
    val showMyOrders: Boolean = true,
    val showMyFavorites: Boolean = true,
    val showSupport: Boolean = true,
    val projectType: ProjectType = ProjectType.WHITELABEL,
    val privacyUrl: String = "",
    var offerUrl: String = ""
)