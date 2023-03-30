package com.progressterra.ipbandroidview.core

import androidx.compose.runtime.Immutable

@Immutable
enum class ScreenState {
    ERROR, LOADING, SUCCESS;

    fun isSuccess(): Boolean = this == SUCCESS

    companion object {

        fun fromBoolean(isSuccess: Boolean): ScreenState = if (isSuccess) SUCCESS else ERROR
    }
}