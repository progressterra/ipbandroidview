package com.progressterra.ipbandroidview.shared

interface Mapper<T, R> {

    fun map(data: T): R
}