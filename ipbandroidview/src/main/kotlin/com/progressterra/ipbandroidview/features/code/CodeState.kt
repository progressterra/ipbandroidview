package com.progressterra.ipbandroidview.features.code

import androidx.compose.runtime.Immutable

@Immutable
data class CodeState(
    val code: String = "",
    val phone: String = "",
    val enabled: Boolean = false
) {

    fun updateCode(newCode: String): CodeState = copy(code = newCode)

    fun updateEnabled(newEnabled: Boolean): CodeState = copy(enabled = newEnabled)

    fun updatePhone(newPhone: String): CodeState = copy(phone = newPhone)
}
