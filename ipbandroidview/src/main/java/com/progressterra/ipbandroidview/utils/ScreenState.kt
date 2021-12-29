package com.progressterra.ipbandroidview.utils

@Deprecated("coz SResult is better", ReplaceWith("SResult"))
enum class ScreenState {
    LOADING,
    DEFAULT,
    ERROR;

    fun isLoading(): Boolean = this == LOADING
    fun isDefault(): Boolean = this == DEFAULT
    fun isError(): Boolean = this == ERROR
}