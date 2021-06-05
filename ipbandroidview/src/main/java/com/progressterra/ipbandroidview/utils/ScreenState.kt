package com.progressterra.ipbandroidview.utils

enum class ScreenState {
    LOADING,
    DEFAULT,
    ERROR;

    fun isLoading(): Boolean = this == LOADING
    fun isDefault(): Boolean = this == DEFAULT
    fun isError(): Boolean = this == ERROR
}