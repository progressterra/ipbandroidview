package com.progressterra.ipbandroidview.utils

internal class ToastBundle(var id: Int? = null, vararg args: String) {

    val args: MutableList<String?> = mutableListOf()

    init {
        this.args.addAll(args.asList())
    }
}