package com.progressterra.ipbandroidview.shared

import androidx.compose.runtime.Immutable

@Immutable
enum class ScreenState {
    ERROR, LOADING, SUCCESS;

    fun isSuccess(): Boolean = this == SUCCESS

    fun isLoading(): Boolean = this == LOADING

    fun isError(): Boolean = this == ERROR

    companion object {

        fun fromBoolean(isSuccess: Boolean): ScreenState = if (isSuccess) SUCCESS else ERROR
    }
}