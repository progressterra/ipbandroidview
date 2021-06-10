package com.progressterra.ipbandroidview.utils

class ToastBundle(var id: Int? = null, vararg args: String) {

    val args: MutableList<String?> = mutableListOf()

    init {
        this.args.addAll(args.asList())
    }
}