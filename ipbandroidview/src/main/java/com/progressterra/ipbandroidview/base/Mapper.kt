package com.progressterra.ipbandroidview.base

interface Mapper<S, R> {

    fun map(data: S): R
}