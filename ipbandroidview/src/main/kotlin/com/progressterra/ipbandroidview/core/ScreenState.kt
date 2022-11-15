package com.progressterra.ipbandroidview.core

import androidx.compose.runtime.Immutable

@Immutable
enum class ScreenState {
    ERROR,
    LOADING,
    SUCCESS;

    fun isSuccess(): Boolean = this == SUCCESS
}