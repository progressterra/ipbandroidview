package com.progressterra.ipbandroidview.core

interface Mapper<S, R> {

    fun map(data: S): R
}