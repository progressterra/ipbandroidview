package com.progressterra.ipbandroidview.features.code

import androidx.compose.runtime.Immutable

@Immutable
data class CodeState(
    val code: String = "",
    val phone: String = "",
    val enabled: Boolean = false
) {

    fun uCode(newCode: String): CodeState = copy(code = newCode)

    fun uEnabled(newEnabled: Boolean): CodeState = copy(enabled = newEnabled)

    fun uPhone(newPhone: String): CodeState = copy(phone = newPhone)
}
