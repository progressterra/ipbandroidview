package com.progressterra.ipbandroidview.core

import com.google.gson.Gson

abstract class AbstractMapper(protected val gson: Gson) {

    protected inline fun <reified T> parse(string: String?): T? {
        return gson.fromJson(string, T::class.java)
    }
}