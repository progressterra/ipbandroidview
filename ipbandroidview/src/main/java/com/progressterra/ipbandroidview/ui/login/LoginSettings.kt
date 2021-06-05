package com.progressterra.ipbandroidview.ui.login

internal data class LoginSettings(
    val agreementEnabled: Boolean,
    val footerEnabled: Boolean,
    val footerImageId: Int? = null,
    val loginFinishListener: OnLoginFlowFinishListener? = null
)